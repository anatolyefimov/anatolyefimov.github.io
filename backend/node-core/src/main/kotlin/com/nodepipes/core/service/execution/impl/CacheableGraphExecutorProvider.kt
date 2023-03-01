package com.nodepipes.core.service.execution.impl

import com.nodepipes.core.client.GraphClient
import com.nodepipes.core.service.execution.GraphExecutor
import com.nodepipes.core.service.execution.GraphExecutorProvider
import com.nodepipes.core.service.execution.NodeExecutorProvider
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CacheableGraphExecutorProvider(
    val nodeExecutorProvider: NodeExecutorProvider,
    val graphClient: GraphClient
) : GraphExecutorProvider {

    //TODO: cahceable

    override fun getExecutor(graphId: Long): Mono<GraphExecutor> {
        return graphClient.getById(graphId).map {
            StatefulGraphExecutor(nodeExecutorProvider, it)
        }
    }

}