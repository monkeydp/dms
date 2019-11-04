package com.monkeydp.daios.dms.sdk.metadata.menu.item.def

import com.monkeydp.daios.dms.sdk.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemInfo

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    val info: MenuItemInfo
    var menuDef: MenuDef?
    
    fun create(): MenuItem
}