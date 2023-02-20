package com.nodepipes.core.model.preprocessing

import com.nodepipes.core.model.node.Node

data class NodeDefinition(
    val internalId: Long,
    val node: Node,
    var children: List<NodeDefinition> = listOf(),
    var parents: List<NodeDefinition> = listOf()
)