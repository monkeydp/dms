package com.monkeydp.daios.dms.sdk.jdbc.node

import com.monkeydp.daios.dms.sdk.metadata.node.def.DbNd
import com.monkeydp.daios.dms.sdk.metadata.node.impl.DbNode
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
object JdbcDbsLoader {
    fun loadDbs(connection: Connection, def: DbNd, sql: String): List<DbNode> {
        val statement = connection.createStatement()
        val nodes = mutableListOf<DbNode>()
        statement.use {
            val resultSet = it.executeQuery(sql)
            resultSet.use {
                while (resultSet.next()) nodes.add(DbNode(def, resultSet.getString(1)))
            }
        }
        return nodes
    }
}