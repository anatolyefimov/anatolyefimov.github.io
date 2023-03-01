package com.nodepipes.core.api

import com.fasterxml.jackson.databind.JsonNode
import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.message.meta.Context
import com.nodepipes.core.domain.messaging.message.meta.Headers
import com.nodepipes.core.domain.messaging.message.meta.Params
import com.nodepipes.core.domain.messaging.message.payload.Payload
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.impl.ImmutableSingleCarrier
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.service.execution.GraphExecutionService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class GraphInvocationHandler(
    val graphExecutionService: GraphExecutionService
) {

    //TODO: make it general for json, xml etc

    fun process(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.bodyToMono(JsonNode::class.java).flatMap { json ->
            graphExecutionService.execute(
                serverRequest.nodeInput(json),
                serverRequest.graphId().toLong()
            ).flatMap { it.getMessage().toResponse() }
        }
    }

    private fun ServerRequest.graphId() = this.pathVariable("graphId")

    private fun ServerRequest.nodeInput(data: JsonNode): SingleMessageCarrier = ImmutableSingleCarrier(
        Message(
            data = Payload.JsonPayload(data),
            headers = Headers.of(headers().asHttpHeaders().toSingleValueMap()),
            context = Context.empty(),
            params = extractNodeParams(),
            source = Node.BASE
        )
    )

    private fun ServerRequest.extractNodeParams(): Params {
        return Params.of(queryParams().toSingleValueMap())
    }

    private fun Message.toResponse(): Mono<ServerResponse> {
        return ServerResponse.ok()
            .headers { it.putAll(headers.mapValues { entry -> mutableListOf(entry.value) }) }
            .body(BodyInserters.fromValue(getPayload<Payload.JsonPayload>().data))
    }

}