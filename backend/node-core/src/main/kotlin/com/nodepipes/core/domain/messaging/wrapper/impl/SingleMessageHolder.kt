package com.nodepipes.core.domain.messaging.wrapper.impl

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.wrapper.NodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput

data class SingleMessageHolder(private val message: Message) : NodeInput, NodeOutput {

    override fun getMessages(): List<Message> {
        return listOf(message)
    }

    override fun combine(node: NodeInput): NodeInput {
        return ManyMessageHolder(node.getMessages() + message)
    }

    override fun getMessage(): Message {
        return message
    }

    override fun toInput(): NodeInput {
        return this.copy(message = message)
    }


}