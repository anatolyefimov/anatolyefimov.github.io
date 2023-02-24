package com.nodepipes.core.domain.execution.action.connection

import com.nodepipes.core.domain.execution.BasicAction
import com.nodepipes.core.domain.messaging.wrapper.BasicNodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import com.nodepipes.core.domain.model.node.connection.ConnectionNodeSection
import com.nodepipes.core.domain.preprocessing.NodeDefinition

class ConnectionAction(
    override val node: NodeDefinition,
    private val connectionNodeSection: ConnectionNodeSection
) : BasicAction {

    override fun apply(input: BasicNodeInput): NodeOutput {
        TODO("Not yet implemented")
    }


}