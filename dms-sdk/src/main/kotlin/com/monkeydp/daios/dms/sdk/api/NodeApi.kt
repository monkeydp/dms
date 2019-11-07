package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadingCtx
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi {
    fun loadConnNode(cp: ConnProfile): ConnNode
    fun loadSubNodes(ctx: NodeLoadingCtx): List<Node>
}