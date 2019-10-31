package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    val info: MenuItemInfo
    val menu: Menu?
}