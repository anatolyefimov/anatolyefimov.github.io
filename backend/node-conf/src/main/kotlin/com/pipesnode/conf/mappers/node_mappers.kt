package com.pipesnode.conf.mappers

import com.pipesnode.conf.api.resource.node.GraphResource
import com.pipesnode.conf.api.resource.node.NodeResource
import com.pipesnode.conf.api.resource.node.connection.ConnectionNodeSectionResource
import com.pipesnode.conf.api.resource.transformation.JsltCodeBlockResource
import com.pipesnode.conf.api.resource.transformation.JsltTransformationResource
import com.pipesnode.conf.model.node.Graph
import com.pipesnode.conf.model.node.Node
import com.pipesnode.conf.model.node.NodePositionType
import com.pipesnode.conf.model.node.connection.ConnectionNodeSection
import com.pipesnode.conf.model.transformation.JsltTransformation

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
    val connectionSectionRes = nodeRes.connectionSection

    nodeModel.connectionSection = connectionSectionRes?.let {
        val section = ConnectionNodeSection().apply {
            connectionId = it.connectionId
            interactionMode = it.interactionMode
            node = nodeModel
        }
        section.transformationBefore = connectionSectionRes.transformationBefore?.map()
        section.transformationAfter = connectionSectionRes.transformationAfter?.map()
        section
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
        node.connectionSection?.let { section ->
            ConnectionNodeSectionResource(
                interactionMode = section.interactionMode,
                connectionId = section.connectionId,
                transformationBefore = section.transformationBefore?.map(),
                transformationAfter = section.transformationAfter?.map()
            )
        },
        node.children.map {
            it.internalId!!
        }
    )
}

fun JsltTransformationResource.map(): JsltTransformation {
    val resource = this
    return JsltTransformation(
        context = resource.context?.jslt,
        headers = resource.headers?.jslt,
        payload = resource.payload?.jslt,
        params = resource.params?.jslt
    )
}

fun JsltTransformation.map(): JsltTransformationResource {
    return JsltTransformationResource(
        context = context?.let { JsltCodeBlockResource(it) },
        payload = payload?.let { JsltCodeBlockResource(it) },
        headers = headers?.let { JsltCodeBlockResource(it) },
        params = params?.let { JsltCodeBlockResource(it) }
    )
}