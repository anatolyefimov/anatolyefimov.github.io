package com.nodepipes.core.model.preprocessing

data class GraphDefinition(
    val id: Long,
    val initNode: NodeDefinition,
    val terminalNode: NodeDefinition
)