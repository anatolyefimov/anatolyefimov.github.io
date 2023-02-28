package com.nodepipes.core.service.execution.impl

import com.nodepipes.core.domain.model.node.Graph
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.domain.model.node.NodePositionType
import com.nodepipes.core.domain.preprocessing.GraphDefinition
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.execution.GraphDefinitionPreprocessor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Service
class CacheableGraphDefinitionPreprocessor : GraphDefinitionPreprocessor {

    //TODO: migrate to normal cache
    //TODO: preprocessing should include validation
    private val cache: ConcurrentHashMap<Long, GraphDefinition> = ConcurrentHashMap()

    override fun process(graph: Graph) = Mono.just(graph).map { init ->
        cache.computeIfAbsent(init.id) { computeGraphDefinition(init) }
    }

    private fun computeGraphDefinition(graph: Graph): GraphDefinition {
        val nodes = graph.nodes
        val internalIdToNode = nodes.associateBy { it.internalId }
        val internalIdToNodeDef = mutableMapOf<Long, NodeDefinition>()

        val startNode = nodes.single { it.positionType == NodePositionType.START }
        val startNodeDef = fillNodeDef(startNode, internalIdToNode, internalIdToNodeDef)
        val terminalNode = internalIdToNodeDef.values.single { it.node.positionType == NodePositionType.TERMINAL }

        return GraphDefinition(graph.id, startNodeDef, terminalNode)
    }

    private fun fillNodeDef(
        node: Node,
        internalIdToNodes: Map<Long, Node>,
        internalIdToNodeDef: MutableMap<Long, NodeDefinition>,
        parentNode: NodeDefinition? = null
    ): NodeDefinition {
        return if (node.internalId in internalIdToNodeDef) {
            //we've already saw this node. Just add parent
            val current = internalIdToNodeDef[node.internalId]!!
            if (parentNode != null) {
                current.parents = current.parents.toMutableList().apply {
                    add(parentNode)
                }
            }
            current
        } else if (node.positionType == NodePositionType.TERMINAL) {
            //we did not see terminal node yet. Just create with parent
            val nodeDef = createNodeDef(parentNode, node)
            internalIdToNodeDef[node.internalId] = nodeDef
            nodeDef
        } else {
            //we did not see the node which is not terminal. Recursivelly create childrens and add single parent
            val nodeDef = createNodeDef(parentNode, node)
            internalIdToNodeDef[node.internalId] = nodeDef.apply {
                children = node.childrenInternalIds.map {
                    internalIdToNodeDef.getOrDefault(
                        it, fillNodeDef(
                            internalIdToNodes.getValue(it), internalIdToNodes, internalIdToNodeDef, nodeDef
                        )
                    )
                }
            }
            nodeDef
        }
    }

    private fun createNodeDef(parentNode: NodeDefinition?, node: Node): NodeDefinition {
        return if (parentNode != null) {
            NodeDefinition(node, listOf(), listOf(parentNode))
        } else {
            NodeDefinition(node)
        }
    }
}