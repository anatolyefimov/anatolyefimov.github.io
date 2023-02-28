package com.nodepipes.core.service.connection

import com.nodepipes.core.domain.model.connection.ConnectionType
import reactor.core.publisher.Mono

interface ConnectionExecutorProvider {

    fun getConnectionExecutor(connectionType: ConnectionType): Mono<ConnectionExecutor>

}