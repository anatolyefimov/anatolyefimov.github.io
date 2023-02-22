package com.nodepipes.core.domain.model.node.connection

import com.nodepipes.core.domain.model.transformation.JsltTransformation

data class ConnectionNodeSection(
    val interactionMode: InteractionMode?,
    val transformationBefore: JsltTransformation?,
    val transformationAfter: JsltTransformation?,
    val connectionId: Long?
)