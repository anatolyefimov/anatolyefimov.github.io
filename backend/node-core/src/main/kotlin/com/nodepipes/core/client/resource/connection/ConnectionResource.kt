package com.nodepipes.core.client.resource.connection

import com.nodepipes.core.domain.model.connection.ConnectionType

data class ConnectionResource(
    val id: Long?,
    val type: ConnectionType,
    val name: String,
    val connectionString: String,
)