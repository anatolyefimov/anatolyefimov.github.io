package com.nodepipes.core.domain.action

import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface OneToOneAction {

    val node: NodeDefinition

    operator fun invoke(input: SingleMessageCarrier): Mono<SingleMessageCarrier>
}