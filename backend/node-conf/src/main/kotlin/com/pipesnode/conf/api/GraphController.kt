package com.pipesnode.conf.api

import com.pipesnode.conf.api.resource.node.GraphResource
import com.pipesnode.conf.api.resource.ResponseWrapper
import com.pipesnode.conf.service.impl.DbGraphService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/graphs")
class GraphController(
    private val service: DbGraphService
) {

    @GetMapping
    fun getGraphs(): Page<GraphResource> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun getGraph(@PathVariable id: Long): ResponseWrapper<GraphResource> {
        return ResponseWrapper.of(service.get(id))
    }

    @PostMapping
    fun saveGraph(@RequestBody graph: GraphResource): ResponseWrapper<GraphResource> {
        return ResponseWrapper.of(service.save(graph))
    }

}