package com.nodepipes.core.client.resource.node

data class GraphResource(
    val id: Long,
    val name: String,
    val nodes: List<NodeResource> = listOf()
)