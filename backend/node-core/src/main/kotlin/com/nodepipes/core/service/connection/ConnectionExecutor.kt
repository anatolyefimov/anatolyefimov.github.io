package com.nodepipes.core.service.connection

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.model.connection.Connection
import com.nodepipes.core.domain.model.node.connection.InteractionMode
import reactor.core.publisher.Mono

interface ConnectionExecutor {

    fun connect(message: Message, interactionMode: InteractionMode, connection: Connection): Mono<Message>

}