package com.monkeydp.daios.dms.sdk.metadata.menu.def

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.MenuItemDef

/**
 * @author iPotato
 * @date 2019/11/4
 */
interface MenuDef {
    val items: List<MenuItemDef>
    
    fun create(): Menu
}