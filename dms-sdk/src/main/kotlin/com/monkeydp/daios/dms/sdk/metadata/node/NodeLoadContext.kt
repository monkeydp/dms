package com.monkeydp.daios.dms.sdk.metadata.node

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.connection.Connection

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeLoadContext(
        val cpId: Long,
        @JsonIgnore
        var connection: Connection,
        val nodePath: NodePath
)