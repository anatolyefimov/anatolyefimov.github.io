package com.nodepipes.core.client

import com.nodepipes.core.client.mapper.ConnectionResourceMapper
import com.nodepipes.core.client.resource.ResponseWrapper
import com.nodepipes.core.client.resource.connection.ConnectionResource
import feign.RequestLine
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@Component
class ConnectionClient(
    private val client: ConnectionClientFeign,
    private val connectionMapper: ConnectionResourceMapper
) {

    fun getById(id: Long) = client.getById(id).map{connectionMapper.map(it.content)}

    @ReactiveFeignClient(name = "connection-client")
    interface ConnectionClientFeign {

        @RequestLine("GET /{id}")
        fun getById(@PathVariable id: Long): Mono<ResponseWrapper<ConnectionResource>>

    }

}
