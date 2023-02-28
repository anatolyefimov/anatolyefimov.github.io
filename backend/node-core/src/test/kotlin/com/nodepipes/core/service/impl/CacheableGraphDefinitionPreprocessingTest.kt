package com.nodepipes.core.service.impl

import com.nodepipes.core.domain.model.node.NodePositionType
import com.nodepipes.core.client.mapper.GraphResourceMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CacheableGraphDefinitionPreprocessingTest {


    private lateinit var preprocessor: GraphResourceMapper

    @BeforeEach
    fun setUp() {
        preprocessor = GraphResourceMapper()
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

        val graphDefinition = preprocessor.map(graph).block()
        print(graphDefinition)
    }

}