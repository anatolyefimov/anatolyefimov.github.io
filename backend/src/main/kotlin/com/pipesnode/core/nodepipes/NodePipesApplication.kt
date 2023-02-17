package com.pipesnode.core.nodepipes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NodePipesApplication

fun main(args: Array<String>) {
	runApplication<NodePipesApplication>(*args)
}
