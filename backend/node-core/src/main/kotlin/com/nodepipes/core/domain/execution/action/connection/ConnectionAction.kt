package com.nodepipes.core.domain.execution.action.connection

import com.nodepipes.core.domain.execution.BasicAction
import com.nodepipes.core.domain.messaging.wrapper.BasicNodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
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
) : BasicAction {

    override fun apply(input: BasicNodeInput): Mono<NodeOutput> {
        TODO("Not yet implemented")
    }


}