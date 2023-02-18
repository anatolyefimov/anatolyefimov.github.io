package com.pipesnode.core.nodepipes.repository

import com.pipesnode.core.nodepipes.model.Graph
import org.springframework.data.jpa.repository.JpaRepository

interface GraphRepository : JpaRepository<Graph, Long>