package com.nodepipes.core.service.execution

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.model.node.NodeType
import com.nodepipes.core.domain.model.node.Node
import reactor.core.publisher.Mono

interface NodeExecutor {

    fun nodeType(): NodeType

    fun execute(input: MessageCarrier, node: Node): Mono<SingleMessageCarrier>

}