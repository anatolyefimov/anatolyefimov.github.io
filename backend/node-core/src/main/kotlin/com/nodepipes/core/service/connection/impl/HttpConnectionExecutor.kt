package com.nodepipes.core.service.connection.impl

import com.fasterxml.jackson.databind.JsonNode
import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.message.meta.Headers
import com.nodepipes.core.domain.messaging.message.payload.Payload
import com.nodepipes.core.domain.model.connection.ConnectionResource
import com.nodepipes.core.domain.model.connection.ConnectionType
import com.nodepipes.core.domain.model.node.connection.InteractionMode
import com.nodepipes.core.service.connection.ConnectionExecutor
import org.springframework.http.HttpMethod
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class HttpConnectionExecutor : ConnectionExecutor {

    override val connectionType: ConnectionType
        get() = ConnectionType.HTTP

    //TODO: абстрагироваться от типа данных
    override fun connect(message: Message, interactionMode: InteractionMode, connection: ConnectionResource): Mono<Message> {
        return WebClient.create(connection.connectionString).method(mapInteractionModeToMethod(interactionMode))
            .uri { uriBuilder ->
                uriBuilder.apply { message.params.entries.forEach { queryParam(it.key, it.value) } }.build()
            }.headers { headers ->
                headers.apply { message.headers.entries.forEach { add(it.key, it.value.toString()) } }
            }.body(message.getBody())
            .exchangeToMono { it.toMessage(message) }
    }

    private fun ClientResponse.toMessage(initMessage: Message): Mono<Message> {
        return Mono.just(this).flatMap { response ->
            response.bodyToMono(JsonNode::class.java).map { body ->
                initMessage.copy(
                    headers = response.headers().map(),
                    data = Payload.JsonPayload(body)
                )
            }
        }
    }

    private fun ClientResponse.Headers.map(): Headers {
        return Headers.of(
            this.asHttpHeaders().map {
                it.key to it.value.joinToString()
            }.toMap()
        )
    }

    private fun Message.getBody(): BodyInserter<JsonNode, ReactiveHttpOutputMessage> {
        return getPayload<Payload.JsonPayload>().let {
            BodyInserters.fromValue(it.data)
        }
    }

    private fun mapInteractionModeToMethod(interactionMode: InteractionMode): HttpMethod {
        return when (interactionMode) {
            InteractionMode.READ -> HttpMethod.GET
            InteractionMode.WRITE -> HttpMethod.POST
            InteractionMode.EDIT -> HttpMethod.PATCH
            InteractionMode.REMOVE -> HttpMethod.DELETE
            InteractionMode.UPDATE -> HttpMethod.PUT
        }
    }

}