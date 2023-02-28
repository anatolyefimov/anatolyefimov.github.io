package com.nodepipes.core.domain.model.node

data class Graph(
    val id: Long,
    val initNode: Node,
    val terminalNode: Node
)