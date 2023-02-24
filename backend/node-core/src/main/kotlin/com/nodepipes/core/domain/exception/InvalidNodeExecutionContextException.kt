package com.nodepipes.core.domain.exception

import com.nodepipes.core.domain.preprocessing.NodeDefinition

open class InvalidNodeExecutionContextException(message: String, nodeDefinition: NodeDefinition) :
    RuntimeException(getMessage(message, nodeDefinition)) {


    companion object {
        fun getMessage(message: String, node: NodeDefinition) = "$message, nodeName: ${node.node.name}"
    }
}