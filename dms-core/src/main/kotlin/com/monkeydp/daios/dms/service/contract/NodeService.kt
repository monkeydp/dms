package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadCtx

/**
 * @author iPotato
 * @date 2019/10/28
 */
interface NodeService {
    fun loadConnNodes(): List<ConnNode>
    fun loadSubNodes(ctx: NodeLoadCtx): List<Node>
}