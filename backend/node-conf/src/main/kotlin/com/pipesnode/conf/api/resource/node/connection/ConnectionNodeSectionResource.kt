package com.pipesnode.conf.api.resource.node.connection

import com.pipesnode.conf.api.resource.transformation.JsltTransformationResource
import com.pipesnode.conf.model.node.connection.InteractionMode

data class ConnectionNodeSectionResource(
    val interactionMode: InteractionMode?,
    val transformationBefore: JsltTransformationResource?,
    val transformationAfter: JsltTransformationResource?,
    val connectionId: Long?
)