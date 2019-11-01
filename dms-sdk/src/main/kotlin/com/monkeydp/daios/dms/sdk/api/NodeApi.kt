package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.metadata.node.main.Node
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi {
    fun loadNodes(ctx: NodeLoadCtx): List<Node>
}