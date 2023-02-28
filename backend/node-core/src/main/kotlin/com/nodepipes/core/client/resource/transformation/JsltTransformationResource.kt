package com.nodepipes.core.client.resource.transformation

data class JsltTransformationResource(
    val context: JsltCodeBlockResource?,
    val payload: JsltCodeBlockResource?,
    val headers: JsltCodeBlockResource?,
    val params: JsltCodeBlockResource?
)