package com.monkeydp.daios.dms.sdk.api.impl

import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget.DB
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadContext
import com.monkeydp.daios.dms.sdk.metadata.node.def.DbNodeNd
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.impl.DbNode
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractJdbcNodeApi : AbstractNodeApi() {
    
    protected abstract val map: Map<Target<*>, Pair<NodeDef, String>>
    
    protected fun getNodeDef(target: Target<*>): NodeDef {
        val (def) = map.getValue(target)
        return def
    }
    
    protected fun getSql(target: Target<*>): String {
        val (_, sql) = map.getValue(target)
        return sql
    }
    
    override fun loadNodes(ctx: NodeLoadContext): List<Node> {
        val nodeDef = getNodeDef(ctx.lastTarget)
        val nodes = mutableListOf<Node>()
        nodeDef.children.forEach { nodes.addAll(loadNodes(ctx, it)) }
        return nodes
    }
    
    private fun loadNodes(ctx: NodeLoadContext, nodeDef: NodeDef): List<Node> {
        return when (nodeDef) {
            is DbNodeNd -> loadDbs(ctx)
            else        -> emptyList()
        }
    }
    
    private fun loadDbs(ctx: NodeLoadContext): List<DbNode> {
        val rawConn = ctx.conn.rawConn as Connection
        val statement = rawConn.createStatement()
        val nodes = mutableListOf<DbNode>()
        val (def, sql) = map.getValue(DB)
        statement.use {
            val resultSet = it.executeQuery(sql)
            resultSet.use {
                while (resultSet.next()) nodes.add(DbNode(def, resultSet.getString(1)))
            }
        }
        return nodes
    }
}