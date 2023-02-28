package com.nodepipes.core.domain.execution

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import reactor.core.publisher.Mono

interface ExecutableUnit {

    fun execute(input: MessageCarrier): Mono<SingleMessageCarrier>

}