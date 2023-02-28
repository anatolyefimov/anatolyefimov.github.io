package com.nodepipes.core.domain.action.impl

import com.nodepipes.core.domain.action.OneToOneAction
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.impl.ImmutableSingleCarrier
import com.nodepipes.core.domain.model.connection.ConnectionResource
import com.nodepipes.core.domain.model.node.connection.InteractionMode
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.connection.ConnectionExecutor
import reactor.core.publisher.Mono

class ConnectionAction(
    override val node: NodeDefinition,
    private val connectionExecutor: ConnectionExecutor,
    private val connection: ConnectionResource,
    private val interactionMode: InteractionMode
) : OneToOneAction {

    override fun invoke(input: SingleMessageCarrier): Mono<SingleMessageCarrier> {
        return connectionExecutor.connect(input.getMessage(), interactionMode, connection).map {
            ImmutableSingleCarrier(it)
        }
    }


}