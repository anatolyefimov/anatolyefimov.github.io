package com.nodepipes.core.domain.execution.action.impl

import com.nodepipes.core.domain.execution.action.OneToOneAction
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.model.connection.Connection
import com.nodepipes.core.domain.model.node.connection.InteractionMode
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.connection.ConnectionExecutor
import reactor.core.publisher.Mono

class ConnectionAction(
    override val node: NodeDefinition,
    private val connectionExecutor: ConnectionExecutor,
    private val connection: Connection,
    private val interactionMode: InteractionMode
) : OneToOneAction {

    override fun apply(input: SingleMessageCarrier): Mono<SingleMessageCarrier> {
        TODO("Not yet implemented")
    }


}