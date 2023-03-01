package com.nodepipes.core.service.execution

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import reactor.core.publisher.Mono

interface GraphExecutionService {

    fun execute(input: MessageCarrier, graphId: Long): Mono<SingleMessageCarrier>

}