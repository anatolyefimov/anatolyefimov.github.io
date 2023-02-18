package com.pipesnode.core.nodepipes.service

import com.pipesnode.core.nodepipes.api.resource.GraphResource
import org.springframework.data.domain.Page

interface GraphService {

    fun getAll(): Page<GraphResource>

    fun get(id: Long): GraphResource

    fun save(graph: GraphResource): GraphResource

}