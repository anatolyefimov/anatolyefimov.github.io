package com.nodepipes.core.domain.exception

import com.nodepipes.core.domain.model.node.Node

open class InvalidNodeExecutionContextException(message: String, node: Node) :
    RuntimeException(getMessage(message, node)) {


    companion object {
        fun getMessage(message: String, node: Node) = "$message, nodeName: ${node.node.name}"
    }
}