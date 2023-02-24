package com.nodepipes.core.domain.execution

import com.nodepipes.core.domain.messaging.wrapper.BasicNodeInput
import com.nodepipes.core.domain.messaging.wrapper.NodeOutput
import com.nodepipes.core.domain.preprocessing.NodeDefinition

interface BasicAction {

    val node: NodeDefinition

    fun apply(input: BasicNodeInput): NodeOutput

}