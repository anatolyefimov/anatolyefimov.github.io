package com.nodepipes.core.service.execution

import com.nodepipes.core.domain.preprocessing.NodeDefinition
import reactor.core.publisher.Mono

interface NodeExecutorProvider {

    fun getNode(node: NodeDefinition): Mono<NodeExecutor>

}