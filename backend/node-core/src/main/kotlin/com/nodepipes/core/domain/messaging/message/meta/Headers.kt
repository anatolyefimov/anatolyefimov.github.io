package com.nodepipes.core.domain.messaging.message.meta

class Headers private constructor(
    private val data: Map<String, Any> = HashMap()
) : Map<String, Any> by data {
    fun merge(headers: Headers): Headers {
        return Headers(this.data + headers.data)
    }

    companion object {
        fun of(data: Map<String, Any>): Headers {
            return Headers(data)
        }
    }
}