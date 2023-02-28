package com.nodepipes.core.client

import com.nodepipes.core.client.mapper.GraphResourceMapper
import com.nodepipes.core.client.resource.node.GraphResource
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@Component
class GraphClient(
    private val client: GraphClientFeign,
    private val mapper: GraphResourceMapper
) {

    fun getById(id: Long) = client.getById(id).map(mapper::map)

    @ReactiveFeignClient(url = "localhost:8080/v1/graphs")
    interface GraphClientFeign {

        @GetMapping("/{id}")
        fun getById(@PathVariable id: Long): Mono<GraphResource>

    }
}
