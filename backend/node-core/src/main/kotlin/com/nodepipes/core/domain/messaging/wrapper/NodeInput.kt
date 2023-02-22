package com.nodepipes.core.domain.messaging.wrapper

import com.nodepipes.core.domain.messaging.message.Message


interface NodeInput {
    fun getMessages(): List<Message>
    fun combine(node: NodeInput): NodeInput
}