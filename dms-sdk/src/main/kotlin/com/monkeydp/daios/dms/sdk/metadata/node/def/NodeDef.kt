package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeDef {
    val structName: String
    val info: NodeInfo
    var parent: NodeDef?
    var children: List<NodeDef>
    var menu: Menu?
}