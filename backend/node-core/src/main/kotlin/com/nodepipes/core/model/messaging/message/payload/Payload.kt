package com.nodepipes.core.model.messaging.message.payload

import com.fasterxml.jackson.databind.JsonNode
import org.w3c.dom.Document

sealed class Payload(
    val type: PayloadType,
    val data: Any
) {

    class JsonPayload(data: JsonNode) : Payload(PayloadType.JSON, data)

    class XmlPayload(data: Document) : Payload(PayloadType.XML, data)

    class RawPayload(data: String) : Payload(PayloadType.RAW, data)
}