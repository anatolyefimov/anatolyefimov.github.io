package com.nodepipes.core.domain.action

import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.model.node.Node
import reactor.core.publisher.Mono

interface OneToOneAction {

    val node: Node

    operator fun invoke(input: SingleMessageCarrier): Mono<SingleMessageCarrier>
}