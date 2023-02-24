package com.nodepipes.core.service.graph

import com.nodepipes.core.domain.execution.node.ExecutableNode
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface ExecutableNodeProvider {

    fun getNode(node: NodeDefinition): Mono<ExecutableNode>

}