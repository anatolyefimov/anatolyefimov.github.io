package com.pipesnode.core.nodepipes.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Graph {

    @Id
    val id: Long? = null

    val name: String? = null

    @OneToOne
    val startNode: Node? = null

}