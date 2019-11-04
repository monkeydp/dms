package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.menu.MenuDef

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    val info: MenuItemInfo
    var menuDef: MenuDef?
    
    fun create(): MenuItem
}