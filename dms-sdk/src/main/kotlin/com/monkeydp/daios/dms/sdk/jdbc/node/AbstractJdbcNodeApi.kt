package com.monkeydp.daios.dms.sdk.jdbc.node

import com.monkeydp.daios.dms.sdk.api.impl.AbstractNodeApi
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadContext
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef

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
    
    protected fun getSql(def: NodeDef) = getSql(def.info.target)
    
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
    
    protected abstract fun loadNodes(ctx: NodeLoadContext, def: NodeDef): List<Node>
}