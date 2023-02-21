package com.nodepipes.core.model.preprocessing

import com.nodepipes.core.model.node.Node
import com.nodepipes.core.model.node.NodePositionType

data class NodeDefinition(
    val node: Node,
    var children: List<NodeDefinition> = listOf(),
    var parents: List<NodeDefinition> = listOf()
) {

    val internalId: Long
        get() = node.internalId

    fun isTerminal() = node.positionType == NodePositionType.TERMINAL

    fun isInitial() = node.positionType == NodePositionType.START

    override fun toString(): String {
        return "NodeDefinition { " +
                "\"internalId\": ${internalId}, " +
                "\"node\": ${node}, " +
                "\"children\": ${children.map { it.internalId }}, " +
                "\"parent\": ${parents.map { it.internalId }} " +
                "}"
    }
}