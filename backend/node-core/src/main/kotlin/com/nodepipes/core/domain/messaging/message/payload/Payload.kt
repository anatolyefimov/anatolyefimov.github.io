package com.nodepipes.core.domain.messaging.message.payload

import com.fasterxml.jackson.databind.JsonNode
import org.w3c.dom.Document

sealed class Payload(val type: PayloadType) {

    class JsonPayload(val data: JsonNode) : Payload(PayloadType.JSON)

    class XmlPayload(val data: Document) : Payload(PayloadType.XML)

    class RawPayload(val data: String) : Payload(PayloadType.RAW)

}