package com.nodepipes.core.service.execution.impl

import com.nodepipes.core.domain.action.impl.JsltTransformAction
import com.nodepipes.core.domain.action.impl.LogAction
import com.nodepipes.core.domain.exception.BadNodeConfigurationException
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.impl.ImmutableSingleCarrier
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.domain.model.node.NodeType
import com.nodepipes.core.domain.model.node.connection.ConnectionNodeSection
import com.nodepipes.core.service.connection.ConnectionExecutorProvider
import com.nodepipes.core.service.connection.ConnectionService
import com.nodepipes.core.service.execution.NodeExecutor
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ConnectionNodeExecutor(
    private val connectionExecutorProvider: ConnectionExecutorProvider,
    private val connectionService: ConnectionService
) : NodeExecutor {
    override fun nodeType(): NodeType {
        return NodeType.STORAGE
    }

    override fun execute(input: MessageCarrier, node: Node): Mono<SingleMessageCarrier> {
        return node.connectionSection?.let { connectionSection ->
            LogAction(node)(input)
                .flatMap { applyTransformBefore(node, input, connectionSection) }
                .flatMap { applyConnectionAction(it, connectionSection) }
                .flatMap { applyTransformAfter(node, it, connectionSection) }
        } ?: LogAction(node)(input)
                .flatMap { passMessage(input, node) }
                .flatMap { markNewSourceNode(it, node) }
    }

    private fun applyTransformBefore(
        node: Node, input: MessageCarrier, connectionSection: ConnectionNodeSection
    ): Mono<SingleMessageCarrier> {
        return if (connectionSection.transformationBefore == null) {
            passMessage(input, node)
        } else {
            JsltTransformAction(node, connectionSection.transformationBefore)(input)
        }
    }

    private fun applyConnectionAction(
        input: SingleMessageCarrier, connectionSection: ConnectionNodeSection
    ): Mono<SingleMessageCarrier> {
        return if (connectionSection.connectionId != null && connectionSection.interactionMode != null) {
            connectionService.getConnectionById(connectionSection.connectionId).flatMap { connection ->
                connectionExecutorProvider.getConnectionExecutor(connection.type).flatMap { executor ->
                    executor.connect(input.getMessage(), connectionSection.interactionMode, connection)
                        .map { ImmutableSingleCarrier(it) }
                }
            }
        } else {
            Mono.just(input)
        }
    }

    private fun applyTransformAfter(
        node: Node, input: SingleMessageCarrier, connectionSection: ConnectionNodeSection
    ): Mono<SingleMessageCarrier> {
        return if (connectionSection.transformationAfter == null) {
            Mono.just(input)
        } else {
            JsltTransformAction(node, connectionSection.transformationAfter)(input)
        }
    }

    private fun passMessage(input: MessageCarrier, node: Node): Mono<SingleMessageCarrier> {
        return if (input.getMessages().size == 1) {
            Mono.just(ImmutableSingleCarrier(input.getMessages().single()))
        } else {
            throw BadNodeConfigurationException("Before Transformation should be defined", node)
        }
    }


}