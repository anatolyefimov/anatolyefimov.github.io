package com.nodepipes.core.domain.messaging.message

class Headers private constructor(
    private val data: HashMap<String, Any> = HashMap()
) : Map<String, Any> by data