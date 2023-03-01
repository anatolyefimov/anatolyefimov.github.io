package com.nodepipes.core.service.execution

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.impl.ImmutableCarrier
import com.nodepipes.core.domain.messaging.wrapper.impl.ImmutableSingleCarrier
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.domain.model.node.NodeType
import reactor.core.publisher.Mono

interface NodeExecutor {

    fun nodeType(): NodeType

    fun execute(input: MessageCarrier, node: Node): Mono<SingleMessageCarrier>

    fun markNewSourceNode(input: SingleMessageCarrier, node: Node): Mono<SingleMessageCarrier> {
        return Mono.just(input).map { carrier ->
            ImmutableSingleCarrier(carrier.getMessage().copy(source = node))
        }
    }

}