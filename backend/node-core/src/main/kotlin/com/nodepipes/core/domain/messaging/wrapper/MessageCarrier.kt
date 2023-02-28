package com.nodepipes.core.domain.messaging.wrapper

import com.nodepipes.core.domain.messaging.message.Message
interface MessageCarrier {
    fun getMessages(): List<Message>
    fun combine(node: MessageCarrier): MessageCarrier
}