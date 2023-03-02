package com.nodepipes.core.client

import com.nodepipes.core.client.mapper.GraphResourceMapper
import com.nodepipes.core.client.resource.ResponseWrapper
import com.nodepipes.core.client.resource.node.GraphResource
import com.nodepipes.core.domain.model.node.Graph
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

    fun getById(id: Long) = client.getById(id).map { mapper.map(it.content) }

    @ReactiveFeignClient(name = "graph-client", url = "localhost:8080/v1/graphs")
    interface GraphClientFeign {

        @GetMapping("/{id}")
        fun getById(@PathVariable id: Long): Mono<ResponseWrapper<GraphResource>>

    }
}
