package com.pipesnode.conf.service

import com.pipesnode.conf.api.resource.GraphResource
import org.springframework.data.domain.Page

interface GraphService {

    fun getAll(): Page<GraphResource>

    fun get(id: Long): GraphResource

    fun save(graph: GraphResource): GraphResource

}