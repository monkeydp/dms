package com.monkeydp.daios.dms.sdk.jdbc.node

import com.monkeydp.daios.dms.sdk.api.impl.AbstractNodeApi
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadContext
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractJdbcNodeApi : AbstractNodeApi() {
    
    override fun loadNodes(ctx: NodeLoadContext): List<Node> {
        val nodeDef = ctx.path.getLastNodeDef()
        val nodes = mutableListOf<Node>()
        nodeDef.children.forEach { nodes.addAll(loadNodes(ctx, it)) }
        return nodes
    }
    
    protected abstract fun loadNodes(ctx: NodeLoadContext, def: NodeDef): List<Node>
}