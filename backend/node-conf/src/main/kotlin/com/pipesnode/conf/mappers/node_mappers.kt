package com.pipesnode.conf.mappers

import com.pipesnode.conf.api.resource.node.ConnectionNodeSectionResource
import com.pipesnode.conf.api.resource.node.GraphResource
import com.pipesnode.conf.api.resource.node.NodeResource
import com.pipesnode.conf.model.node.ConnectionNodeSection
import com.pipesnode.conf.model.node.Graph
import com.pipesnode.conf.model.node.Node
import com.pipesnode.conf.model.node.NodePositionType

fun GraphResource.map(): Graph {
    val resource = this
    val nodes = resource.nodes
    val internalIdToNodesResource = nodes.associateBy { it.internalId }
    val internalIdToFilledNodes = mutableMapOf<Long, Node>()

    val startNodeRes = nodes.single { it.positionType == NodePositionType.START }
    fillNodesAndPutToMap(resource, startNodeRes, internalIdToNodesResource, internalIdToFilledNodes)

    val graph = Graph().apply {
        this.id = resource.id
        this.name = resource.name
        this.nodes = internalIdToFilledNodes.map { entry -> entry.value }
    }
    graph.nodes.forEach {
        it.graph = graph
    }
    return graph
}

private fun fillNodesAndPutToMap(
    graph: GraphResource, nodeRes: NodeResource, internalIdToNodesResource: Map<Long, NodeResource>,
    internalIdToFilledNodes: MutableMap<Long, Node>
): Node {
    val node = nodeRes.map()
    return if (node.internalId in internalIdToFilledNodes) {
        internalIdToFilledNodes[node.internalId!!]!!
    } else if (node.positionType == NodePositionType.TERMINAL) {
        internalIdToFilledNodes[node.internalId!!] = node
        node
    } else {
        internalIdToFilledNodes[node.internalId!!] = node.apply {
            children = nodeRes.childrenInternalIds.map {
                internalIdToFilledNodes.getOrDefault(
                    it, fillNodesAndPutToMap(
                        graph, internalIdToNodesResource.getValue(it),
                        internalIdToNodesResource, internalIdToFilledNodes
                    )
                )
            }
        }
        node
    }
}

private fun NodeResource.map(): Node {
    val nodeRes = this
    val nodeModel = Node().apply {
        id = nodeRes.id
        internalId = nodeRes.internalId
        name = nodeRes.name
        positionType = nodeRes.positionType
        nodeType = nodeRes.nodeType
    }
    nodeModel.connectionSection = nodeRes.connectionSection?.let {
        ConnectionNodeSection().apply {
            connectionId = it.connectionId
            interactionMode = it.interactionMode
            node = nodeModel
        }
    }
    return nodeModel
}

fun Graph.map(): GraphResource {
    val graph = this
    return GraphResource(
        graph.id,
        graph.name!!,
        graph.nodes.map { it.map() }
    )
}

private fun Node.map(): NodeResource {
    val node = this
    return NodeResource(
        node.id,
        node.internalId!!,
        node.name!!,
        node.positionType!!,
        node.nodeType!!,
        node.connectionSection?.let {
            ConnectionNodeSectionResource(it.interactionMode!!, it.connectionId!!)
        },
        node.children.map {
            it.internalId!!
        }
    )
}