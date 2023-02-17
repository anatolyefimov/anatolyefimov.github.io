package com.pipesnode.core.nodepipes.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
class Node {
    @Id
    val id: Long? = null

    val name: String? = null

    @ManyToMany
    val childNodes: List<Node> = listOf()

}