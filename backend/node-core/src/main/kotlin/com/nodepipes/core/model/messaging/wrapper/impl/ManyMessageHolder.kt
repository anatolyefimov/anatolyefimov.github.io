package com.nodepipes.core.model.messaging.wrapper.impl

import com.nodepipes.core.model.messaging.message.Message
import com.nodepipes.core.model.messaging.wrapper.NodeInput

data class ManyMessageHolder(private val messages: List<Message>) : NodeInput {

    override fun getMessages(): List<Message> {
        return ArrayList(messages)
    }

    override fun combine(node: NodeInput): NodeInput {
        return this.copy(messages = node.getMessages() + messages)
    }

}