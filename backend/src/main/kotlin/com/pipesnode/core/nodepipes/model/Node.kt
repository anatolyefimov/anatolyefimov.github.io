package com.pipesnode.core.nodepipes.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity
class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var internalId: Long? = null

    var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var graph: Graph? = null

    var positionType: NodePositionType? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "node_child_node",
        joinColumns = [JoinColumn(name = "node_id")],
        inverseJoinColumns = [JoinColumn(name = "child_node_id")]
    )
    var children: List<Node> = listOf()

}