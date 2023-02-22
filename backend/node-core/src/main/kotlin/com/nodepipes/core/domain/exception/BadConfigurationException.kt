package com.nodepipes.core.domain.exception

open class BadConfigurationException : RuntimeException {

    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}