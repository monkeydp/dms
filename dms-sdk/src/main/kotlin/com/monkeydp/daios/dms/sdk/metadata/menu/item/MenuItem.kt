package com.monkeydp.daios.dms.sdk.metadata.menu.item

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