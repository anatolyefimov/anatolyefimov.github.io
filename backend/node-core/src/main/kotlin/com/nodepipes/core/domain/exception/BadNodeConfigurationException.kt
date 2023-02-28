package com.nodepipes.core.domain.exception

import com.nodepipes.core.domain.model.node.Node

open class BadNodeConfigurationException(
    message: String,
    node: Node
) : InvalidNodeExecutionContextException(message, node)