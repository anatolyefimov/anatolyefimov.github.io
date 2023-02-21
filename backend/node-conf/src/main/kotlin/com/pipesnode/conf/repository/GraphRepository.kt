package com.pipesnode.conf.repository

import com.pipesnode.conf.model.node.Graph
import org.springframework.data.jpa.repository.JpaRepository

interface GraphRepository : JpaRepository<Graph, Long>