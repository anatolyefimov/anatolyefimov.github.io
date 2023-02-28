package com.nodepipes.core.domain.messaging.wrapper.impl

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier

data class ImmutableCarrier(private val messages: List<Message>) :
    MessageCarrier {

    override fun getMessages(): List<Message> {
        return ArrayList(messages)
    }

    override fun combine(node: MessageCarrier): MessageCarrier {
        return this.copy(messages = node.getMessages() + messages)
    }

}