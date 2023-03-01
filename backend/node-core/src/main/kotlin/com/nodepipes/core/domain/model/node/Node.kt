package com.nodepipes.core.domain.model.node

import com.nodepipes.core.domain.model.node.connection.ConnectionNodeSection

data class Node(
    val id: Long,
    val internalId: Long,
    val name: String,
    val positionType: NodePositionType,
    val nodeType: NodeType,
    val connectionSection: ConnectionNodeSection?,
    var children: List<Node> = listOf(),
    var parents: List<Node> = listOf()
) {
    fun isTerminal() = positionType == NodePositionType.TERMINAL

    fun isInitial() = positionType == NodePositionType.START
    override fun toString(): String {
        return "NodeDefinition(" +
                "id=$id, " +
                "internalId=$internalId, " +
                "name='$name', " +
                "positionType=$positionType, " +
                "nodeType=$nodeType, " +
                "connectionSection=$connectionSection, " +
                "children=${children.map { it.internalId }}, " +
                "parents=${parents.map { it.internalId }}" +
                ")"
    }

    companion object {
        val BASE = Node(
            Long.MIN_VALUE,
            Long.MIN_VALUE,
            "BASE",
            NodePositionType.INTERMEDIATE,
            NodeType.STORAGE,
            null
        )
    }

}