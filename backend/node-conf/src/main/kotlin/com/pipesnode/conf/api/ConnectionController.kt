package com.pipesnode.conf.api

import com.pipesnode.conf.api.resource.ResponseWrapper
import com.pipesnode.conf.api.resource.connection.ConnectionResource
import com.pipesnode.conf.service.ConnectionService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/connections")
class ConnectionController(
    private val service: ConnectionService
) {

    @GetMapping
    fun getConnections(): Page<ConnectionResource> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun getConnection(@PathVariable id: Long): ResponseWrapper<ConnectionResource> {
        return ResponseWrapper.of(service.get(id))
    }

    @PostMapping
    fun saveGraph(@RequestBody connection: ConnectionResource): ResponseWrapper<ConnectionResource> {
        return ResponseWrapper.of(service.save(connection))
    }

}