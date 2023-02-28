package com.nodepipes.core.service.connection

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.model.connection.ConnectionResource
import com.nodepipes.core.domain.model.connection.ConnectionType
import com.nodepipes.core.domain.model.node.connection.InteractionMode
import reactor.core.publisher.Mono

interface ConnectionExecutor {

    val connectionType: ConnectionType

    fun connect(message: Message, interactionMode: InteractionMode, connection: ConnectionResource): Mono<Message>

}