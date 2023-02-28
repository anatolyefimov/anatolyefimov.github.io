package com.nodepipes.core.domain.messaging.message

import com.nodepipes.core.domain.exception.WrongExpectedDatatypeException
import com.nodepipes.core.domain.messaging.message.meta.Context
import com.nodepipes.core.domain.messaging.message.meta.Headers
import com.nodepipes.core.domain.messaging.message.meta.Params
import com.nodepipes.core.domain.messaging.message.payload.Payload
import com.nodepipes.core.domain.model.node.Node

data class Message(
    val headers: Headers,
    val context: Context,
    val params: Params,
    val source: Node,
    val data: Payload,
) {

    inline fun <reified T : Payload> getPayload(): T {
        if (data is T) {
            return data
        } else {
            throw WrongExpectedDatatypeException("Expected datatype: ${T::class}, real: ${data::class}", source)
        }
    }

}