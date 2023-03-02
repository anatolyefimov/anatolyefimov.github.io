package com.nodepipes.core.client.mapper

import com.nodepipes.core.client.resource.node.GraphResource
import com.nodepipes.core.client.resource.node.NodeResource
import com.nodepipes.core.client.resource.transformation.JsltTransformationResource
import com.nodepipes.core.domain.model.node.Graph
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.domain.model.node.NodePositionType
import com.nodepipes.core.domain.model.node.connection.ConnectionNodeSection
import com.nodepipes.core.domain.model.transformation.JsltCodeBlock
import com.nodepipes.core.domain.model.transformation.JsltTransformation
import org.springframework.stereotype.Service

@Service
class GraphResourceMapper {


    fun map(graph: GraphResource) = computeGraph(graph)

    private fun computeGraph(graph: GraphResource): Graph {
        val nodes = graph.nodes
        val internalIdToNode = nodes.associateBy { it.internalId }
        val internalIdToNodeDef = mutableMapOf<Long, Node>()

        val startNode = nodes.single { it.positionType == NodePositionType.START }
        val startNodeDef = fillNodeDef(startNode, internalIdToNode, internalIdToNodeDef)
        val terminalNode = internalIdToNodeDef.values.single { it.positionType == NodePositionType.TERMINAL }

        return Graph(graph.id, startNodeDef, terminalNode)
    }

    private fun fillNodeDef(
        node: NodeResource,
        internalIdToNodes: Map<Long, NodeResource>,
        internalIdToNodeDef: MutableMap<Long, Node>,
        parentNode: Node? = null
    ): Node {
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
            val nodeDef = createNode(parentNode, node)
            internalIdToNodeDef[node.internalId] = nodeDef
            nodeDef
        } else {
            //we did not see the node which is not terminal. Recursivelly create childrens and add single parent
            val nodeDef = createNode(parentNode, node)
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

    private fun createNode(parentNode: Node?, node: NodeResource): Node {
        return if (parentNode != null) {
            mapNode(node).apply {
                parents = listOf(parentNode)
            }
        } else {
            mapNode(node)
        }
    }

    private fun mapNode(node: NodeResource): Node {
        return Node(
            node.id,
            node.internalId,
            node.name,
            node.positionType,
            node.nodeType,
            node.connectionSection?.let { connectionSection ->
                ConnectionNodeSection(
                    connectionSection.interactionMode,
                    connectionSection.transformationBefore?.let { mapTransformation(it) },
                    connectionSection.transformationAfter?.let { mapTransformation(it) },
                    connectionSection.connectionId
                )
            }
        )
    }

    private fun mapTransformation(jslt: JsltTransformationResource): JsltTransformation {
        return JsltTransformation(
            jslt.context?.let { JsltCodeBlock(it.jslt) },
            jslt.payload?.let { JsltCodeBlock(it.jslt) },
            jslt.headers?.let { JsltCodeBlock(it.jslt) },
            jslt.params?.let { JsltCodeBlock(it.jslt) },
        )
    }
}