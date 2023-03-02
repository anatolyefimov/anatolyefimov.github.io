package com.nodepipes.core.service.connection.impl

import com.nodepipes.core.client.ConnectionClient
import com.nodepipes.core.domain.model.connection.Connection
import com.nodepipes.core.service.connection.ConnectionService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CacheableConnectionService(
    private val client: ConnectionClient
) : ConnectionService {

    //TODO: cacheable
    override fun getConnectionById(id: Long): Mono<Connection> {
        return client.getById(id)
    }

}