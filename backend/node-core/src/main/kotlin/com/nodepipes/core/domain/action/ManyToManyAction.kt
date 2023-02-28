package com.nodepipes.core.domain.action

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface ManyToManyAction {

    val node: NodeDefinition

    operator fun invoke(input: MessageCarrier): Mono<MessageCarrier>

}