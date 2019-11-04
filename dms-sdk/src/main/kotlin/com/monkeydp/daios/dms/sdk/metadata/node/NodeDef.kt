package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.menu.MenuDef

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