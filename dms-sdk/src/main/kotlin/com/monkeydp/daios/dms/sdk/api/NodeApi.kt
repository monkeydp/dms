package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadingCtx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi : Api {
    fun loadConnNodes(cps: Iterable<ConnProfile>): List<ConnNode>
    fun loadSubNodes(ctx: NodeLoadingCtx): List<Node>
}