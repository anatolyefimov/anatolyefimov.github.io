package com.nodepipes.core.service

import com.nodepipes.core.model.execution.impl.ExecutableNode
import com.nodepipes.core.model.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface ExecutableNodeProvider {

    fun getNode(node: NodeDefinition): Mono<ExecutableNode>

}