package com.nodepipes.core.domain.messaging.message

class Context private constructor(
    private val data: HashMap<String, Any> = HashMap()
) : Map<String, Any> by data {


    companion object {
        fun create(context: Context): Context {
            return Context(HashMap(context.data))
        }

        fun empty(): Context {
            return Context(HashMap())
        }

        fun combine(context1: Context, context2: Context): Context {
            return Context(HashMap(context1.data + context2.data))
        }
    }

}