package com.nodepipes.core.domain.execution.node

import com.nodepipes.core.domain.execution.ExecutableUnit
import com.nodepipes.core.domain.messaging.wrapper.NodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

class ExecutableNode(private val definition: NodeDefinition) : ExecutableUnit {

    override fun execute(input: NodeInput): Mono<NodeOutput> {
        println("${definition.internalId} : ${Thread.currentThread()}")
        return Mono.just(NodeOutput())
    }

}