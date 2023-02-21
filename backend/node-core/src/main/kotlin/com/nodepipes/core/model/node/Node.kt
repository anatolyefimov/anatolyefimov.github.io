package com.nodepipes.core.model.node

data class Node(
    val id: Long,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val nodeType: NodeType,
    val childrenInternalIds: List<Long>
)