package com.nodepipes.core.client

import com.nodepipes.core.domain.model.node.Graph
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(url = "localhost:8080/v1/graphs")
interface GraphClientV1 {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Mono<Graph>

}