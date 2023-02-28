package com.nodepipes.core.domain.execution.action

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface ManyToManyAction {

    val node: NodeDefinition

    fun apply(input: MessageCarrier): Mono<MessageCarrier>

}