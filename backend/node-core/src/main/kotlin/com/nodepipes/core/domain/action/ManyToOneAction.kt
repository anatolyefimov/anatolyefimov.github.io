package com.nodepipes.core.domain.action

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.model.node.Node
import reactor.core.publisher.Mono

interface ManyToOneAction {

    val node: Node

    operator fun invoke(input: MessageCarrier): Mono<SingleMessageCarrier>

}