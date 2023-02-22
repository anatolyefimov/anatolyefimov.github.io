package com.nodepipes.core.model.messaging.message

import com.nodepipes.core.model.exception.WrongExpectedDatatypeException
import com.nodepipes.core.model.messaging.message.payload.Payload
import com.nodepipes.core.model.preprocessing.NodeDefinition

data class Message(
    val headers: Headers,
    val context: Context,
    val params: Params,
    val source: NodeDefinition,
    val data: Payload,
) {

    inline fun <reified T : Payload> getPayload(): T {
        if (data is T) {
            return data
        } else {
            throw WrongExpectedDatatypeException("Expected datatype: ${T::class}, real: ${data::class}")
        }
    }

}