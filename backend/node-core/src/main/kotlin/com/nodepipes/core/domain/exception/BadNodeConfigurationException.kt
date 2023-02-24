package com.nodepipes.core.domain.exception

import com.nodepipes.core.domain.preprocessing.NodeDefinition

open class BadNodeConfigurationException(
    message: String,
    nodeDefinition: NodeDefinition
) : InvalidNodeExecutionContextException(message, nodeDefinition)