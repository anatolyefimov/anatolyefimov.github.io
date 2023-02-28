package com.nodepipes.core.client.resource.node.connection

import com.nodepipes.core.client.resource.transformation.JsltTransformationResource
import com.nodepipes.core.domain.model.node.connection.InteractionMode

data class ConnectionNodeSectionResource(
    val interactionMode: InteractionMode?,
    val transformationBefore: JsltTransformationResource?,
    val transformationAfter: JsltTransformationResource?,
    val connectionId: Long?
)