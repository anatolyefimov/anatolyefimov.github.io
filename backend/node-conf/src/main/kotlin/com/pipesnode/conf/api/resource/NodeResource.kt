package com.pipesnode.conf.api.resource

import com.pipesnode.conf.model.NodePositionType

data class NodeResource(
    val id: Long?,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val childrenInternalIds: List<Long>
)