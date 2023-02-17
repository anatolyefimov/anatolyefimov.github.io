package com.pipesnode.core.nodepipes.api.resource

data class GraphResource(
    val id: Long?,
    val name: String,
    val startNodeId: Long?,
    val nodeList: List<Long>
)