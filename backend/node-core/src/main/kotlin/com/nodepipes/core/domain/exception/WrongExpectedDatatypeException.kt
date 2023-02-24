package com.nodepipes.core.domain.exception

import com.nodepipes.core.domain.preprocessing.NodeDefinition

class WrongExpectedDatatypeException(override val message: String, val node: NodeDefinition) :
    InvalidNodeExecutionContextException(message, node)