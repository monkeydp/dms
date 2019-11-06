package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadCtx
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi {
    fun loadConnNode(cp: ConnProfile): ConnNode
    fun loadSubNodes(ctx: NodeLoadCtx): List<Node>
}