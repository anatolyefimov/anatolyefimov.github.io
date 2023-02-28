package com.nodepipes.core.domain.execution

import com.nodepipes.core.domain.execution.node.ExecutableGraph
import com.nodepipes.core.domain.execution.node.ConnectionNode
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.domain.model.node.NodePositionType
import com.nodepipes.core.domain.preprocessing.GraphDefinition
import com.nodepipes.core.domain.preprocessing.NodeDefinition
import com.nodepipes.core.service.graph.ExecutableNodeProvider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono

@ExtendWith(MockitoExtension::class)
class ExecutableGraphTest {

    private val nodeProvider: ExecutableNodeProvider = object : ExecutableNodeProvider {
        override fun getNode(node: NodeDefinition): Mono<ExecutableUnit> = Mono.just(ConnectionNode(node))
    }

    @Test
    fun testGraph() {
        val node1 = Node(1, 1, "1", NodePositionType.START, listOf(2, 4, 3))
        val node2 = Node(2, 2, "2", NodePositionType.INTERMEDIATE, listOf(5, 4))
        val node3 = Node(3, 3, "3", NodePositionType.INTERMEDIATE, listOf(4))
        val node4 = Node(4, 4, "4", NodePositionType.INTERMEDIATE, listOf(6))
        val node5 = Node(5, 5, "5", NodePositionType.INTERMEDIATE, listOf(4))
        val node6 = Node(6, 6, "6", NodePositionType.TERMINAL, listOf())

        val nodeDefinition6 = NodeDefinition(node6)
        val nodeDefinition4 = NodeDefinition(node4, listOf(nodeDefinition6))
        val nodeDefinition5 = NodeDefinition(node5, listOf(nodeDefinition4))
        val nodeDefinition3 = NodeDefinition(node3, listOf(nodeDefinition4))
        val nodeDefinition2 = NodeDefinition(node2, listOf(nodeDefinition5, nodeDefinition4))
        val nodeDefinition1 = NodeDefinition(node1, listOf(nodeDefinition2, nodeDefinition3, nodeDefinition4))

        nodeDefinition6.parents = listOf(nodeDefinition4)
        nodeDefinition5.parents = listOf(nodeDefinition2)
        nodeDefinition4.parents = listOf(nodeDefinition5, nodeDefinition2, nodeDefinition1, nodeDefinition3)
        nodeDefinition3.parents = listOf(nodeDefinition1)
        nodeDefinition2.parents = listOf(nodeDefinition1)

        val graphDefinition = GraphDefinition(1L, nodeDefinition1, nodeDefinition6)

        val block = ExecutableGraph(nodeProvider, graphDefinition).execute(MessageCarrier()).block()
    }

    @Test
    fun testTest() {
        val A = Mono.just("").flatMap {
            print("A")
            return@flatMap Mono.just("")
        }.cache()

        val B = A.flatMap {
            print("B")
            return@flatMap Mono.just("")
        }


        val C = A.flatMap {
            print("C")
            return@flatMap Mono.just("")
        }

        B.block();
        C.block();
    }

}