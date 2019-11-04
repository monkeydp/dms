package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface NodeDef {
    val structName: String
    val info: NodeInfo
    var parent: NodeDef?
    var children: List<NodeDef>
    var menuDef: MenuDef?
}