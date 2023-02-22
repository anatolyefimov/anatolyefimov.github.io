package com.nodepipes.core.service.impl

import com.nodepipes.core.domain.model.node.Graph
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.domain.model.node.NodePositionType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CacheableGraphDefinitionPreprocessingTest {


    private lateinit var preprocessor: CacheableGraphDefinitionPreprocessor

    @BeforeEach
    fun setUp() {
        preprocessor = CacheableGraphDefinitionPreprocessor()
    }

    @Test
    fun testGraphDefinitionComputing() {
        val graph = Graph(
            100L, "test",
            listOf(
                Node(1, 1, "1", NodePositionType.START, listOf(2, 4, 3)),
                Node(2, 2, "2", NodePositionType.INTERMEDIATE, listOf(5, 4)),
                Node(3, 3, "3", NodePositionType.INTERMEDIATE, listOf(4)),
                Node(4, 4, "4", NodePositionType.INTERMEDIATE, listOf(6)),
                Node(5, 5, "5", NodePositionType.INTERMEDIATE, listOf(4)),
                Node(6, 6, "6", NodePositionType.TERMINAL, listOf()),

                )
        )

        val graphDefinition = preprocessor.process(graph).block()
        print(graphDefinition)
    }

}