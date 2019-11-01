package com.monkeydp.daios.dms.sdk.metadata.menu.item.main

import com.monkeydp.daios.dms.sdk.metadata.menu.item.info.MenuItemInfo

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItem {
    val info: MenuItemInfo
    /**
     * Weather the item has sub menu
     */
    val hasSub: Boolean
}