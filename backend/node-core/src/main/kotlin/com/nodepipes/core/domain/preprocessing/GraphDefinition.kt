package com.nodepipes.core.domain.preprocessing

data class GraphDefinition(
    val id: Long,
    val initNode: NodeDefinition,
    val terminalNode: NodeDefinition
)