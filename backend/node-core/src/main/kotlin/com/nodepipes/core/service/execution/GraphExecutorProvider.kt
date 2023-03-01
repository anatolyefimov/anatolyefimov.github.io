package com.nodepipes.core.service.execution

import reactor.core.publisher.Mono

interface GraphExecutorProvider {

    fun getExecutor(graphId: Long): Mono<GraphExecutor>

}