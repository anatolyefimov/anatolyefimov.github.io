package com.pipesnode.core.nodepipes.api

import com.pipesnode.core.nodepipes.api.resource.GraphResource
import com.pipesnode.core.nodepipes.api.resource.ResponseWrapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/graphs")
class GraphController {

    @GetMapping
    fun getGraphs(): Page<GraphResource> {
        return PageImpl(
            listOf(
                GraphResource(
                    1L, "Hui pizda", 1L, listOf()
                ),
                GraphResource(
                    1L, "Hui pizda", 1L, listOf()
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getGraphs(@PathVariable id: Long): ResponseWrapper<GraphResource> {
        return ResponseWrapper.of(
            GraphResource(
                id, "Hui pizda", 1L, listOf()
            )
        )
    }

}