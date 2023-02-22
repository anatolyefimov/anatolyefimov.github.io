package com.pipesnode.conf.model.node.connection

import com.pipesnode.conf.model.node.Node
import com.pipesnode.conf.model.transformation.JsltTransformation
import io.hypersistence.utils.hibernate.type.json.JsonType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
@TypeDef(name = "json", typeClass = JsonType::class)
class ConnectionNodeSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var connectionId: Long? = null

    @Enumerated(EnumType.STRING)
    var interactionMode: InteractionMode? = null

    @OneToOne
    @JoinColumn(name = "node_id", referencedColumnName = "id")
    var node: Node? = null

    @Type(type = "json")
    var transformationBefore: JsltTransformation? = null

    @Type(type = "json")
    var transformationAfter: JsltTransformation? = null
}