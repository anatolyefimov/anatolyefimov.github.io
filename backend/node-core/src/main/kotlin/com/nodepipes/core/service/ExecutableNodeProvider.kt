package com.nodepipes.core.service

import com.nodepipes.core.model.execution.node.ExecutableNode
import com.nodepipes.core.model.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface ExecutableNodeProvider {

    fun getNode(node: NodeDefinition): Mono<ExecutableNode>

}