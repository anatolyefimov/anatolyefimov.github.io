package com.nodepipes.core.domain.action

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface ManyToOneAction {

    val node: NodeDefinition

    operator fun invoke(input: MessageCarrier): Mono<SingleMessageCarrier>

}