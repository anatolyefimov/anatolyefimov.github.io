package com.nodepipes.core.domain.model.node

data class Graph(
    var id: Long,
    var name: String,
    var nodes: List<Node> = listOf()
)