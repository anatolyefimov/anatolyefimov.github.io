package com.nodepipes.core.service.connection.impl

import com.nodepipes.core.domain.model.connection.ConnectionType
import com.nodepipes.core.service.connection.ConnectionExecutor
import com.nodepipes.core.service.connection.ConnectionExecutorProvider
import org.springframework.stereotype.Component

@Component
class ContextBasedConnectionExecutorProvider(
    connectionExecutors: List<ConnectionExecutor>
) : ConnectionExecutorProvider {

    private val connectionExecutors = connectionExecutors.associateBy { it.connectionType }

    override fun getConnectionExecutor(connectionType: ConnectionType): ConnectionExecutor =
        connectionExecutors.getValue(connectionType)
}