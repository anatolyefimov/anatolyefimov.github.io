package com.nodepipes.core.domain.execution

import com.nodepipes.core.service.execution.impl.ConnectionNodeExecutor
import com.nodepipes.core.domain.messaging.wrapper.MessageCarrier
import com.nodepipes.core.domain.model.node.NodePositionType
import com.nodepipes.core.domain.model.node.Graph
import com.nodepipes.core.domain.model.node.Node
import com.nodepipes.core.service.execution.NodeExecutorProvider
import com.nodepipes.core.service.execution.GraphExecutor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono

@ExtendWith(MockitoExtension::class)
class GraphExecutorImplTest {

    private val nodeProvider: NodeExecutorProvider = object : NodeExecutorProvider {
        override fun getNode(node: Node): Mono<com.nodepipes.core.service.execution.GraphExecutor> = Mono.just(
            ConnectionNodeExecutor(node)
        )
    }

    @Test
    fun testGraph() {
        val node1 = Node(1, 1, "1", NodePositionType.START, listOf(2, 4, 3))
        val node2 = Node(2, 2, "2", NodePositionType.INTERMEDIATE, listOf(5, 4))
        val node3 = Node(3, 3, "3", NodePositionType.INTERMEDIATE, listOf(4))
        val node4 = Node(4, 4, "4", NodePositionType.INTERMEDIATE, listOf(6))
        val node5 = Node(5, 5, "5", NodePositionType.INTERMEDIATE, listOf(4))
        val node6 = Node(6, 6, "6", NodePositionType.TERMINAL, listOf())

        val nodeDefinition6 = Node(node6)
        val nodeDefinition4 = Node(node4, listOf(nodeDefinition6))
        val nodeDefinition5 = Node(node5, listOf(nodeDefinition4))
        val nodeDefinition3 = Node(node3, listOf(nodeDefinition4))
        val nodeDefinition2 = Node(node2, listOf(nodeDefinition5, nodeDefinition4))
        val nodeDefinition1 = Node(node1, listOf(nodeDefinition2, nodeDefinition3, nodeDefinition4))

        nodeDefinition6.parents = listOf(nodeDefinition4)
        nodeDefinition5.parents = listOf(nodeDefinition2)
        nodeDefinition4.parents = listOf(nodeDefinition5, nodeDefinition2, nodeDefinition1, nodeDefinition3)
        nodeDefinition3.parents = listOf(nodeDefinition1)
        nodeDefinition2.parents = listOf(nodeDefinition1)

        val graphDefinition = Graph(1L, nodeDefinition1, nodeDefinition6)

        val block = GraphExecutor(nodeProvider, graphDefinition).execute(MessageCarrier()).block()
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