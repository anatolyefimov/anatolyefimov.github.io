package com.pipesnode.conf.api.resource.node

data class GraphResource(
    val id: Long?,
    val name: String,
    val nodes: List<NodeResource>
)