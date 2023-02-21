package com.pipesnode.conf.mappers

import com.pipesnode.conf.api.resource.connection.ConnectionResource
import com.pipesnode.conf.model.connection.Connection

fun ConnectionResource.map(): Connection {
    val connectionResource = this
    return Connection().apply {
        id = connectionResource.id
        type = connectionResource.type
        name = connectionResource.name
        connectionString = connectionResource.connectionString
    }
}

fun Connection.map(): ConnectionResource {
    val connection = this
    return ConnectionResource(
        connection.id,
        connection.type!!,
        connection.name!!,
        connection.connectionString!!
    )
}