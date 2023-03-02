package com.nodepipes.core.domain.action.impl

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.NullNode
import com.nodepipes.core.domain.exception.InvalidNodeExecutionContextException
import com.nodepipes.core.domain.action.ManyToOneAction
import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.message.payload.Payload
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.impl.ImmutableSingleCarrier
import com.nodepipes.core.domain.model.transformation.JsltCodeBlock
import com.nodepipes.core.domain.model.transformation.JsltTransformation
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.util.objectMapper
import com.schibsted.spt.data.jslt.Parser
import reactor.core.publisher.Mono

class JsltTransformAction(
    override val node: Node,
    private val transformation: JsltTransformation
) : ManyToOneAction {

    override fun invoke(input: MessageCarrier): Mono<SingleMessageCarrier> {
        return Mono.just(constructVars(input)).map { vars ->
            ImmutableSingleCarrier(
                Message(
                    headers = transform(transformation.headers, vars) {
                        input.getMessages().map { it.headers }.reduceRight { a, b -> a.merge(b) }
                    },
                    context = transform(transformation.context, vars) {
                        input.getMessages().map { it.context }.reduceRight { a, b -> a.merge(b) }
                    },
                    params = transform(transformation.params, vars) {
                        input.getMessages().map { it.params }.reduceRight { a, b -> a.merge(b) }
                    },
                    data = Payload.JsonPayload(
                        transform(transformation.payload, vars) {
                            throw InvalidNodeExecutionContextException(
                                "Transformation with empty body section could not be applied", node
                            )
                        }
                    ),
                    source = node
                )
            ).apply {
                print(this.getMessage().getPayload())
            }
        }
    }

    private inline fun <reified T> transform(jslt: JsltCodeBlock?, vars: Map<String, JsonNode>, default: () -> T): T {
        return if (jslt != null) {
            transform(jslt, vars)
        } else {
            default()
        }
    }

    private inline fun <reified T> transform(jslt: JsltCodeBlock, vars: Map<String, JsonNode>): T {
        val compiled = Parser.compileString(jslt.jslt)
        val result = compiled.apply(vars, NullNode.instance)
        return objectMapper.convertValue(result, T::class.java)
    }


    private fun constructVars(input: MessageCarrier): Map<String, JsonNode> {
        val mainVars = input.getMessages().map {
            mutableMapOf<String, JsonNode>(
                "${it.source.name}_headers" to objectMapper.valueToTree(it.headers),
                "${it.source.name}_params" to objectMapper.valueToTree(it.params),
                "${it.source.name}_context" to objectMapper.valueToTree(it.context),
                "${it.source.name}_payload" to objectMapper.valueToTree(it.getPayload<Payload.JsonPayload>().data),
            )
        }.reduceRight { map, acc -> acc.apply { putAll(map) } }

        if (input.getMessages().size == 1) {
            val message = input.getMessages().single()
            mainVars.apply {
                this["headers"] = objectMapper.valueToTree(message.headers)
                this["params"] = objectMapper.valueToTree(message.params)
                this["context"] = objectMapper.valueToTree(message.context)
                this["payload"] = objectMapper.valueToTree(message.getPayload<Payload.JsonPayload>().data)
            }
        }

        return mainVars
    }
}