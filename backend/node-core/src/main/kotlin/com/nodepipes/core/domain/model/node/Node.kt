package com.nodepipes.core.domain.model.node

import com.nodepipes.core.domain.model.node.connection.ConnectionNodeSection

data class Node(
    val id: Long,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val nodeType: NodeType,
    val connectionSection: ConnectionNodeSection?,
    val childrenInternalIds: List<Long>
)