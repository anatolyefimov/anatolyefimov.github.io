package com.nodepipes.core.service.execution.impl

import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.messaging.wrapper.SingleMessageCarrier
import com.nodepipes.core.domain.preprocessing.GraphDefinition
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.execution.NodeExecutorProvider
import com.nodepipes.core.service.execution.GraphExecutor
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.concurrent.ConcurrentHashMap

class GraphExecutorImpl(
    private val nodeProvider: NodeExecutorProvider, private val definition: GraphDefinition
) : GraphExecutor {

    private val internalIdToOutput: ConcurrentHashMap<Long, Mono<SingleMessageCarrier>> = ConcurrentHashMap()

    override fun execute(input: MessageCarrier): Mono<SingleMessageCarrier> {
        return executeGraph(input, definition.terminalNode)
    }

    private fun executeGraph(input: MessageCarrier, definition: NodeDefinition): Mono<SingleMessageCarrier> {
        return if (definition.isInitial()) {
            getOutput(definition.internalId) {
                nodeProvider.getNode(definition).flatMap { it.execute(input) }
            }
        } else if (definition.parents.size == 1) {
            nodeProvider.getNode(definition).flatMap { current ->
                getOutput(definition.parents.single().internalId) {
                    executeGraph(input, definition.parents.single())
                }.flatMap { current.execute(it) }
            }
        } else {
            Flux.fromArray(definition.parents.toTypedArray()).flatMap { node ->
                getOutput(node.internalId) { executeGraph(input, node) }
            }.map { it as MessageCarrier }.reduce { in1, in2 -> in1.combine(in2) }.flatMap {
                nodeProvider.getNode(definition).flatMap { exec -> exec.execute(it) }
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