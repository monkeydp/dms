package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadContext

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi {
    fun loadNodes(context: NodeLoadContext): List<Node>
}