package com.nodepipes.core.model.execution

import com.nodepipes.core.model.messaging.wrapper.NodeInput
import com.nodepipes.core.model.messaging.wrapper.NodeOutput
import reactor.core.publisher.Mono

interface Action {

    fun apply(input: NodeInput): Mono<NodeOutput>

}