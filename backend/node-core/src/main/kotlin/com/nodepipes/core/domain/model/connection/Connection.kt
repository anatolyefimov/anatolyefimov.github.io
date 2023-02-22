package com.nodepipes.core.domain.model.connection

data class Connection(
    val id: Long?,
    val type: ConnectionType,
    val name: String,
    val connectionString: String,
)