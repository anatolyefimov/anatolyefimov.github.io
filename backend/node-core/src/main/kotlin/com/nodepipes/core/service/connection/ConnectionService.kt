package com.nodepipes.core.service.connection

import com.nodepipes.core.domain.model.connection.ConnectionResource
import reactor.core.publisher.Mono

interface ConnectionService {

    fun getConnectionById(id: Long): Mono<ConnectionResource>

}