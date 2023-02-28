package com.nodepipes.core.client.mapper

import com.nodepipes.core.client.resource.connection.ConnectionResource
import com.nodepipes.core.config.DefaultMapperConfig
import com.nodepipes.core.domain.model.connection.Connection
import org.mapstruct.Mapper

@Mapper(config = DefaultMapperConfig::class)
interface ConnectionResourceMapper {

    fun map(resource: ConnectionResource): Connection

}