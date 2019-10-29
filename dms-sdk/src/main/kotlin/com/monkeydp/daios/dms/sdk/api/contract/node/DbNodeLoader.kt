package com.monkeydp.daios.dms.sdk.api.contract.node

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.node.def.DbNd
import com.monkeydp.daios.dms.sdk.metadata.node.impl.DbNode
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
interface DbNodeLoader {
    fun loadDbNodes(connection: Connection, def: DbNd, sql: String): List<DbNode>
}