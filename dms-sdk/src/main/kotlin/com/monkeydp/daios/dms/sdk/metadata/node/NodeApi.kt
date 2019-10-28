package com.monkeydp.daios.dms.sdk.metadata.node

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi {
    fun loadNodes(context: NodeLoadContext): List<Node>
}