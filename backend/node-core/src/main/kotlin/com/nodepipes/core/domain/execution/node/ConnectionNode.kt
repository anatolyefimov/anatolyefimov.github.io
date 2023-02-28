package com.nodepipes.core.domain.execution.node

import com.nodepipes.core.domain.execution.ExecutableUnit
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

class ConnectionNode(private val definition: NodeDefinition) : ExecutableUnit {

    override fun execute(input: MessageCarrier): Mono<SingleMessageCarrier> {
        println("${definition.internalId} : ${Thread.currentThread()}")
        return Mono.just(SingleMessageCarrier())
    }

}