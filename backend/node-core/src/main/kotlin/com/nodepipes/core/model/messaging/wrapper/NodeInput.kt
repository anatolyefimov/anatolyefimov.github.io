package com.nodepipes.core.model.messaging.wrapper

import com.nodepipes.core.model.messaging.message.Message


interface NodeInput {
    fun getMessages(): List<Message>
    fun combine(node: NodeInput): NodeInput
}