package com.pipesnode.conf.model.connection

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Connection {

    @Id
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    var type: ConnectionType? = null

    var name: String? = null

    var connectionString: String? = null

}