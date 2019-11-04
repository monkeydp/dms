package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemDef

/**
 * @author iPotato
 * @date 2019/11/4
 */
interface MenuDef {
    val items: List<MenuItemDef>
    
    fun create(): Menu
}