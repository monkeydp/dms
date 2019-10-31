package com.monkeydp.daios.dms.sdk.metadata.node.ctx

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfoPath

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeLoadContext(
        var conn: Conn<*>,
        val path: NodeInfoPath
) {
    companion object {
        fun mock(conn: Conn<*>, path: NodeInfoPath) =
                NodeLoadContext(conn, path)
    }
}