package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeDef {
    val structName: String
    val info: NodeInfo
    val parent: NodeDef?
    var children: List<NodeDef>
}