package com.nodepipes.core.service.execution

import com.nodepipes.core.domain.model.node.Node
import reactor.core.publisher.Mono

interface NodeExecutorProvider {

    fun getNode(node: Node): Mono<NodeExecutor>

}