package com.pipesnode.core.nodepipes.api.resource

import com.pipesnode.core.nodepipes.model.NodePositionType

data class NodeResource(
    val id: Long?,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val childrenInternalIds: List<Long>
)