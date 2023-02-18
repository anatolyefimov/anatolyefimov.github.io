package com.pipesnode.core.nodepipes.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    @OneToMany(mappedBy = "graph", cascade = [CascadeType.ALL])
    var nodes: List<Node> = listOf()

}