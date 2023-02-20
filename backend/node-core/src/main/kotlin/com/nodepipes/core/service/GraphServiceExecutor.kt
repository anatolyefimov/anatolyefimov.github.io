package com.nodepipes.core.service

import com.nodepipes.core.model.messaging.NodeInput
import com.nodepipes.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

interface GraphServiceExecutor {

    fun execute(graphId: Long, input: NodeInput): Mono<NodeOutput>

}