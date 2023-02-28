package com.nodepipes.core.service.graph

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import reactor.core.publisher.Mono

interface GraphExecutor {

    fun execute(graphId: Long, input: MessageCarrier): Mono<SingleMessageCarrier>

}