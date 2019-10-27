package com.monkeydp.daios.dms.sdk.metadata.node

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeDef {
    val info: NodeInfo
    val parent: NodeDef?
    val children: MutableList<NodeDef>
    
    fun appendChild(vararg nodeDefs: NodeDef) {
        children.addAll(nodeDefs.toList())
    }
}