package com.nodepipes.core.execution

import com.nodepipes.core.model.messaging.NodeInput
import com.nodepipes.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

interface ExecutableNode {

    fun execute(input: Mono<NodeInput>): Mono<NodeOutput>

}