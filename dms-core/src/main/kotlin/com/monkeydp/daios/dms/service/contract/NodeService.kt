package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.node.main.Node
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtxForm
import com.monkeydp.daios.dms.sdk.metadata.node.main.ConnNode

/**
 * @author iPotato
 * @date 2019/10/28
 */
interface NodeService {
    fun loadConnNodes(): List<ConnNode>
    fun loadSubNodes(ctx: NodeLoadCtxForm): List<Node>
}