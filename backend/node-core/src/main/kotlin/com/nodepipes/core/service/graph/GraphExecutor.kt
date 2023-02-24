package com.nodepipes.core.service.graph

import com.nodepipes.core.domain.messaging.wrapper.NodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import reactor.core.publisher.Mono

interface GraphExecutor {

    fun execute(graphId: Long, input: NodeInput): Mono<NodeOutput>

}