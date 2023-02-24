package com.nodepipes.core.domain.messaging.wrapper.impl

import com.nodepipes.core.domain.messaging.message.Message
import com.nodepipes.core.domain.messaging.wrapper.NodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import com.nodepipes.core.domain.messaging.wrapper.BasicNodeInput

data class BasicMessageCarrier(private val message: Message) : BasicNodeInput, NodeOutput {

    override fun getMessages(): List<Message> {
        return listOf(message)
    }

    override fun combine(node: NodeInput): NodeInput {
        return MessageCarrier(node.getMessages() + message)
    }

    override fun getMessage(): Message {
        return message
    }

    override fun toInput(): NodeInput {
        return this.copy(message = message)
    }


}