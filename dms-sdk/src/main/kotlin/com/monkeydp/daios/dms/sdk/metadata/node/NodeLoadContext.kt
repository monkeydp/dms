package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.conn.Conn

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeLoadContext(
        var conn: Conn<*>,
        val path: NodeInfoPath
) {
    val lastTarget
        get() = path.last().target
    
    companion object {
        fun mock(conn: Conn<*>, path: NodeInfoPath) = NodeLoadContext(conn, path)
    }
}