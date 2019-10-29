package com.monkeydp.daios.dms.sdk.jdbc.node

import com.monkeydp.daios.dms.sdk.metadata.node.def.TableNd
import com.monkeydp.daios.dms.sdk.metadata.node.impl.TableNode
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
object JdbcTablesLoader {
    fun loadTables(connection: Connection, def: TableNd, sql: String): List<TableNode> {
        val statement = connection.createStatement()
        val nodes = mutableListOf<TableNode>()
        statement.use {
            val resultSet = it.executeQuery(sql)
            resultSet.use {
                while (resultSet.next()) nodes.add(TableNode(def, resultSet.getString(1)))
            }
        }
        return nodes
    }
}