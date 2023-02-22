package com.pipesnode.conf.api.resource.transformation

data class JsltTransformationResource(
    val context: JsltCodeBlockResource?,
    val payload: JsltCodeBlockResource?,
    val headers: JsltCodeBlockResource?,
    val params: JsltCodeBlockResource?
)