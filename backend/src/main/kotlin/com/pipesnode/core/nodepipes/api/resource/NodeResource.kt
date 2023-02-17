package com.pipesnode.core.nodepipes.api.resource

data class NodeResource(
    val id: Long?,
    val name: String?,
    val childNodes: List<NodeResource>
)