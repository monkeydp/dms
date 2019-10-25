package com.monkeydp.daios.dms.sdk.metadata.node

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeDef {
    val type: NodeType<*>
    val info: NodeInfo
    val children: List<NodeDef>
}