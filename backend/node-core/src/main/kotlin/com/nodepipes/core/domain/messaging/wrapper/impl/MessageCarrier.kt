package com.nodepipes.core.domain.messaging.wrapper.impl

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.wrapper.NodeInput

data class MessageCarrier(private val messages: List<Message>) : NodeInput {

    override fun getMessages(): List<Message> {
        return ArrayList(messages)
    }

    override fun combine(node: NodeInput): NodeInput {
        return this.copy(messages = node.getMessages() + messages)
    }

}