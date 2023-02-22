package com.nodepipes.core.model.exception

class WrongExpectedDatatypeException(override val message: String) : BadConfigurationException(message)