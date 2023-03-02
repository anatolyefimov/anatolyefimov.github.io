package com.nodepipes.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactivefeign.spring.config.EnableReactiveFeignClients

@SpringBootApplication
@EnableReactiveFeignClients
class NodeCoreApplication

fun main(args: Array<String>) {
    runApplication<NodeCoreApplication>(*args)
}
