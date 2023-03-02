package com.nodepipes.core.service.execution.impl

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.model.node.Graph
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.service.execution.GraphExecutor
import com.nodepipes.core.service.execution.NodeExecutorProvider
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.concurrent.ConcurrentHashMap

class StatefulGraphExecutor(
    private val nodeProvider: NodeExecutorProvider, private val graph: Graph
) : GraphExecutor {

    private val internalIdToOutput: ConcurrentHashMap<Long, Mono<SingleMessageCarrier>> = ConcurrentHashMap()

    override fun execute(input: MessageCarrier): Mono<SingleMessageCarrier> {
        return executeGraph(input, graph.terminalNode)
    }

    private fun executeGraph(input: MessageCarrier, node: Node): Mono<SingleMessageCarrier> {
        return if (node.isInitial()) {
            getOutput(node.internalId) {
                nodeProvider.getNode(node).flatMap { it.execute(input, node) }
            }
        } else if (node.parents.size == 1) {
            nodeProvider.getNode(node).flatMap { current ->
                getOutput(node.parents.single().internalId) {
                    executeGraph(input, node.parents.single())
                }.flatMap { current.execute(it, node) }
            }
        } else {
            Flux.fromArray(node.parents.toTypedArray()).flatMap { parentNode ->
                getOutput(parentNode.internalId) { executeGraph(input, parentNode) }
            }.map { it as MessageCarrier }.reduce { in1, in2 -> in1.combine(in2) }.flatMap {
                nodeProvider.getNode(node).flatMap { exec -> exec.execute(it, node) }
            }
        }
    }

    private inline fun getOutput(internalId: Long, calc: () -> Mono<SingleMessageCarrier>): Mono<SingleMessageCarrier> {
        return if (internalIdToOutput.containsKey(internalId)) {
            internalIdToOutput[internalId]!!
        } else {
            val result = calc().cache().publishOn(Schedulers.boundedElastic())
            internalIdToOutput[internalId] = result
            result
        }
    }
}