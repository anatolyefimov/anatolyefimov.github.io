package com.pipesnode.conf.service

import com.pipesnode.conf.api.resource.connection.ConnectionResource
import org.springframework.data.domain.Page

interface ConnectionService {

    fun getAll(): Page<ConnectionResource>

    fun get(id: Long): ConnectionResource

    fun save(connection: ConnectionResource): ConnectionResource
}