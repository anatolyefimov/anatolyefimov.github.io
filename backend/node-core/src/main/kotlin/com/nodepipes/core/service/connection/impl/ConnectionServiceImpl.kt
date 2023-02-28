package com.nodepipes.core.service.connection.impl

import com.nodepipes.core.client.ConnectionClient
import com.nodepipes.core.domain.model.connection.Connection
import com.nodepipes.core.service.connection.ConnectionService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ConnectionServiceImpl(
    private val client: ConnectionClient
) : ConnectionService {

    override fun getConnectionById(id: Long): Mono<Connection> {
        TODO("Not yet implemented")
    }

}