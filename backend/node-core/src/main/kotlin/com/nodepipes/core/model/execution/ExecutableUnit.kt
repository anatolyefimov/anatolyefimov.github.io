package com.nodepipes.core.model.execution

import com.nodepipes.core.model.messaging.NodeInput
import com.nodepipes.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

interface ExecutableUnit {

    fun execute(input: NodeInput): Mono<NodeOutput>

}