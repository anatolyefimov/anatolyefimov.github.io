package com.pipesnode.conf.service.impl

import com.pipesnode.conf.api.resource.connection.ConnectionResource
import com.pipesnode.conf.mappers.map
import com.pipesnode.conf.repository.ConnectionRepository
import com.pipesnode.conf.service.ConnectionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service

@Service
class DbBasedConnectionService(
    private val repository: ConnectionRepository
) : ConnectionService {
    override fun getAll(): Page<ConnectionResource> {
        return PageImpl(repository.findAll()).map { it.map() }
    }

    override fun get(id: Long): ConnectionResource {
        return repository.getReferenceById(id).map()
    }

    override fun save(connection: ConnectionResource): ConnectionResource {
        return repository.save(connection.map()).map()
    }
}