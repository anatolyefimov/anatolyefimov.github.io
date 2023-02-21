package com.nodepipes.core.model.messaging


//TODO
class NodeInput {

    fun toOutput(): NodeOutput {
        return NodeOutput()
    }

    fun combine(node: NodeInput): NodeInput {
        return NodeInput()
    }
}