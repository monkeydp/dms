package com.monkeydp.daios.dms.sdk.metadata.node

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.util.IdUtil.INVALID_ID

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeLoadContext(
        val cpId: Long = INVALID_ID,
        @JsonIgnore
        val conn: Conn,
        val nodePath: NodePath
)