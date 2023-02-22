package com.pipesnode.conf.api.resource.node

import com.pipesnode.conf.api.resource.node.connection.ConnectionNodeSectionResource
import com.pipesnode.conf.model.node.NodePositionType
import com.pipesnode.conf.model.node.NodeType

data class NodeResource(
    val id: Long?,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val nodeType: NodeType,
    val connectionSection: ConnectionNodeSectionResource?,
    val childrenInternalIds: List<Long>
)