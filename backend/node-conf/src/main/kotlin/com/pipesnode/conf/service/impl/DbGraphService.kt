package com.pipesnode.conf.service.impl

import com.pipesnode.conf.api.resource.node.GraphResource
import com.pipesnode.conf.mappers.map
import com.pipesnode.conf.repository.GraphRepository
import com.pipesnode.conf.service.GraphService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service

@Service
class DbGraphService(
    private val repo: GraphRepository
) : GraphService {

    override fun getAll(): Page<GraphResource> {
        return PageImpl(repo.findAll()).map { it.map() }
    }

    override fun get(id: Long): GraphResource {
        return repo.getReferenceById(id).map()
    }

    override fun save(graph: GraphResource): GraphResource {
        return repo.save(graph.map()).map()
    }

}