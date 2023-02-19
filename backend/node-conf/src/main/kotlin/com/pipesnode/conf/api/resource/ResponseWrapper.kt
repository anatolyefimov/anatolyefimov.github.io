package com.pipesnode.conf.api.resource

data class ResponseWrapper<T> private constructor(
    val content: T
) {

    companion object {
        fun <T> of(content: T): ResponseWrapper<T> {
            return ResponseWrapper(content)
        }
    }
}