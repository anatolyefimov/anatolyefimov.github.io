package com.pipesnode.conf.repository

import com.pipesnode.conf.model.Graph
import org.springframework.data.jpa.repository.JpaRepository

interface GraphRepository : JpaRepository<Graph, Long>