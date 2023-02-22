package com.pipesnode.conf.model.transformation

data class JsltTransformation(
    var context: String?,
    var payload: String?,
    var headers: String?,
    var params: String?
)