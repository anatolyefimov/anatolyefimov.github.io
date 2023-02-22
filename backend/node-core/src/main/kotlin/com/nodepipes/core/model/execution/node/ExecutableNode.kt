package com.nodepipes.core.model.execution.node

import com.nodepipes.core.model.execution.ExecutableUnit
import com.nodepipes.core.model.messaging.wrapper.NodeInput
import com.nodepipes.core.model.messaging.wrapper.NodeOutput
import com.nodepipes.core.model.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

class ExecutableNode(private val definition: NodeDefinition) : ExecutableUnit {

    override fun execute(input: NodeInput): Mono<NodeOutput> {
        println("${definition.internalId} : ${Thread.currentThread()}")
        return Mono.just(NodeOutput())
    }

}