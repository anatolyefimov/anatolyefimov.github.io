package com.nodepipes.core.domain.execution.action.impl

import com.nodepipes.core.domain.execution.action.ManyToManyAction
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.util.logger
import reactor.core.publisher.Mono


class LogAction(override val node: NodeDefinition) : ManyToManyAction {

    private val log = this.logger()

    override fun apply(input: MessageCarrier): Mono<MessageCarrier> {
        return Mono.just(input).doOnSuccess {
            log.info("node: {}, messages: {}", node.node, it.getMessages())
        }
    }
}