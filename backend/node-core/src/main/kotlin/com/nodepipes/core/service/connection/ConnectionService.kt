package com.nodepipes.core.service.connection

import com.nodepipes.core.domain.model.connection.Connection

interface ConnectionService {

    fun getConnectionById(id: Long): Connection

}