package com.nodepipes.core.domain.messaging.message.meta

class Context private constructor(
    private val data: Map<String, Any> = HashMap()
) : Map<String, Any> by data {

    fun merge(context: Context): Context {
        return Context(this.data + context.data)
    }


    companion object {
        fun create(context: Context): Context {
            return Context(context.data)
        }

        fun empty(): Context {
            return Context(HashMap())
        }

        fun combine(context1: Context, context2: Context): Context {
            return Context(context1.data + context2.data)
        }
    }

}