package com.pipesnode.conf.api.resource.connection

import com.pipesnode.conf.model.connection.ConnectionType

data class ConnectionResource(
    val id: Long?,
    val type: ConnectionType,
    val name: String,
    val connectionString: String,
)