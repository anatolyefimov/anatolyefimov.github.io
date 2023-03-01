package com.nodepipes.core.service.execution.impl

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.service.execution.GraphExecutionService
import com.nodepipes.core.service.execution.GraphExecutorProvider
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GraphExecutionServiceImpl(
    val graphExecutorProvider: GraphExecutorProvider
) : GraphExecutionService {

    override fun execute(input: MessageCarrier, graphId: Long): Mono<SingleMessageCarrier> {
        return graphExecutorProvider.getExecutor(graphId).flatMap {
            it.execute(input)
        }
    }

}