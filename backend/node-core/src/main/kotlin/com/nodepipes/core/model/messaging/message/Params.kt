package com.nodepipes.core.model.messaging.message

class Params private constructor(
    private val data: HashMap<String, Any> = HashMap()
) : Map<String, Any> by data