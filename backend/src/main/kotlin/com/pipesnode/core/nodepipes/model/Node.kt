package com.pipesnode.core.nodepipes.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Node {
    @Id
    val id: Long? = null

    val name: String? = null

    @ManyToMany
    val childNodes: List<Node> = listOf()

}