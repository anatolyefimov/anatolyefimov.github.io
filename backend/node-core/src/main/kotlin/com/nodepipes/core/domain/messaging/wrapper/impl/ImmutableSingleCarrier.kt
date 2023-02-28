package com.nodepipes.core.domain.messaging.wrapper.impl

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier

data class ImmutableSingleCarrier(private val message: Message): SingleMessageCarrier {

    override fun getMessages(): List<Message> {
        return listOf(message)
    }

    override fun combine(node: MessageCarrier): MessageCarrier {
        return ImmutableCarrier(node.getMessages() + message)
    }

    override fun getMessage(): Message {
        return message
    }

}