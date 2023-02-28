package com.nodepipes.core.service.connection

import com.nodepipes.core.domain.model.connection.ConnectionType

interface ConnectionExecutorProvider {

    fun getConnectionExecutor(connectionType: ConnectionType): ConnectionExecutor

}