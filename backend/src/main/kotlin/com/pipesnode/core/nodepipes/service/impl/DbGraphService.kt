package com.pipesnode.core.nodepipes.service.impl

import com.pipesnode.core.nodepipes.api.resource.GraphResource
import com.pipesnode.core.nodepipes.mappers.map
import com.pipesnode.core.nodepipes.repository.GraphRepository
import com.pipesnode.core.nodepipes.service.GraphService
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
        var entity = graph.map()
        return repo.save(entity).map()
    }

}