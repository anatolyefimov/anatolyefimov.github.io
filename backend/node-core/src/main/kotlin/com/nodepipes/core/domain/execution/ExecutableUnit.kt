package com.nodepipes.core.domain.execution

import com.nodepipes.core.domain.messaging.wrapper.NodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import reactor.core.publisher.Mono

interface ExecutableUnit {

    fun execute(input: NodeInput): Mono<NodeOutput>

}