package com.pipesnode.core.nodepipes.api.resource

data class GraphResource(
    val id: Long?,
    val name: String,
    val nodes: List<NodeResource>
)