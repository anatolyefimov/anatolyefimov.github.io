package com.pipesnode.core.nodepipes.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
class Graph {

    @Id
    val id: Long? = null

    val name: String? = null

    @OneToOne
    val startNode: Node? = null

}