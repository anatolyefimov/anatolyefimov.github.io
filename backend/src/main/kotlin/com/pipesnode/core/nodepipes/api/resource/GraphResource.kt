package com.pipesnode.core.nodepipes.api.resource

data class GraphResource(
    val id: Long?,
    val name: String,
    val startNodeId: Long?,
    val nodes: List<Long>
)