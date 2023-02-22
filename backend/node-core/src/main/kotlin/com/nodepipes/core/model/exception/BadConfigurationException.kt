package com.nodepipes.core.model.exception

open class BadConfigurationException : RuntimeException {

    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}