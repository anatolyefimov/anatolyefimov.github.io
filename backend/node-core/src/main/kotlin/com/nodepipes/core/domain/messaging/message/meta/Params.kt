package com.nodepipes.core.domain.messaging.message.meta

class Params private constructor(
    private val data: Map<String, Any> = HashMap()
) : Map<String, Any> by data {
    fun merge(params: Params): Params {
        return Params(this.data + params.data)
    }
}