package com.pipesnode.conf.repository

import com.pipesnode.conf.model.connection.Connection
import org.springframework.data.jpa.repository.JpaRepository

interface ConnectionRepository : JpaRepository<Connection, Long>