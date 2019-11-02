package com.monkeydp.daios.dms.sdk.metadata.node.ctx

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeLoadCtx(
        var conn: Conn<*>,
        val path: NodePath
)