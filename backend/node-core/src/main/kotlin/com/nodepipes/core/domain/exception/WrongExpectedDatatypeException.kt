package com.nodepipes.core.domain.exception

import com.nodepipes.core.domain.model.node.Node

class WrongExpectedDatatypeException(override val message: String, val node: Node) :
    InvalidNodeExecutionContextException(message, node)