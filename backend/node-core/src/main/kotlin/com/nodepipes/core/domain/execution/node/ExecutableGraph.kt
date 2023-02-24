package com.nodepipes.core.domain.execution.node

import com.nodepipes.core.domain.execution.ExecutableUnit
import com.nodepipes.core.domain.messaging.wrapper.NodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import com.nodepipes.core.domain.preprocessing.GraphDefinition
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.graph.ExecutableNodeProvider
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.concurrent.ConcurrentHashMap

class ExecutableGraph(
    private val nodeProvider: ExecutableNodeProvider, private val definition: GraphDefinition
) : ExecutableUnit {

    private val internalIdToOutput: ConcurrentHashMap<Long, Mono<NodeOutput>> = ConcurrentHashMap()

    override fun execute(input: NodeInput): Mono<NodeOutput> {
        return executeGraph(input, definition.terminalNode)
    }

    private fun executeGraph(input: NodeInput, definition: NodeDefinition): Mono<NodeOutput> {
        return if (definition.isInitial()) {
            getOutput(definition.internalId) {
                nodeProvider.getNode(definition).flatMap { it.execute(input) }
            }
        } else if (definition.parents.size == 1) {
            nodeProvider.getNode(definition).flatMap { current ->
                getOutput(definition.parents.single().internalId) {
                    executeGraph(input, definition.parents.single())
                }.flatMap { current.execute(it.toInput()) }
            }
        } else {
            Flux.fromArray(definition.parents.toTypedArray()).flatMap { node ->
                getOutput(node.internalId) { executeGraph(input, node) }
            }.map { out -> out.toInput() }.reduce { in1, in2 -> in1.combine(in2) }.flatMap {
                nodeProvider.getNode(definition).flatMap { exec -> exec.execute(it) }
            }
        }
    }

    private inline fun getOutput(internalId: Long, calc: () -> Mono<NodeOutput>): Mono<NodeOutput> {
        return if (internalIdToOutput.containsKey(internalId)) {
            internalIdToOutput[internalId]!!
        } else {
            val result = calc().cache().publishOn(Schedulers.boundedElastic())
            internalIdToOutput[internalId] = result
            result
        }
    }
}