package com.nodepipes.core.domain.exception

class WrongExpectedDatatypeException(override val message: String) : BadConfigurationException(message)