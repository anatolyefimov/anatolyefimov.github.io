package com.nodepipes.core.domain.execution.action

import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface OneToOneAction {

    val node: NodeDefinition

    fun apply(input: SingleMessageCarrier): Mono<SingleMessageCarrier>
}