package com.nodepipes.core.domain.model.transformation

data class JsltTransformation(
    val context: JsltCodeBlock?,
    val payload: JsltCodeBlock?,
    val headers: JsltCodeBlock?,
    val params: JsltCodeBlock?
)