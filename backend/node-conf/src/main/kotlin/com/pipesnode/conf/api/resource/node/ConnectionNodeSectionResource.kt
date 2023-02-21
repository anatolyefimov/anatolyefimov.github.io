package com.pipesnode.conf.api.resource.node

import com.pipesnode.conf.model.node.InteractionMode

data class ConnectionNodeSectionResource(
    val interactionMode: InteractionMode,
    val connectionId: Long
)