package com.nodepipes.core.client.resource.node

import com.nodepipes.core.client.resource.node.connection.ConnectionNodeSectionResource
import com.nodepipes.core.domain.model.node.NodePositionType
import com.nodepipes.core.domain.model.node.NodeType

data class NodeResource(
    val id: Long?,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val nodeType: NodeType,
    val connectionSection: ConnectionNodeSectionResource?,
    val childrenInternalIds: List<Long>
)