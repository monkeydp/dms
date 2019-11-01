package com.monkeydp.daios.dms.sdk.metadata.menu.item.def

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.info.MenuItemInfo

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    val info: MenuItemInfo
    val menu: Menu?
}