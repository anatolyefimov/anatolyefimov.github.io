package com.nodepipes.core.service.graph.impl

import com.nodepipes.core.domain.execution.ExecutableUnit
import com.nodepipes.core.domain.execution.node.ConnectionNode
import com.nodepipes.core.domain.model.node.NodeType
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.graph.ExecutableNodeProvider
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class ExecutableNodeProviderImpl : ExecutableNodeProvider {

    override fun getNode(node: NodeDefinition): Mono<ExecutableUnit> {
        return when (node.node.nodeType) {
            NodeType.STORAGE -> ConnectionNode(node)
        }.toMono()
    }

}