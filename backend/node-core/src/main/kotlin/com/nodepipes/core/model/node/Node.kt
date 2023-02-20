package com.nodepipes.core.model.node

data class Node(
    var id: Long,
    var internalId: Long,
    var name: String,
    var positionType: NodePositionType,
    val childrenInternalIds: List<Long>
)