package com.pipesnode.conf.model.node

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class ConnectionNodeSection {

    @Id
    var id: Long? = null

    var connectionId: Long? = null

    @Enumerated(EnumType.STRING)
    var interactionMode: InteractionMode? = null

    @OneToOne
    @JoinColumn(name = "node_id", referencedColumnName = "id")
    var node: Node? = null

}