package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface Menu {
    val items: List<MenuItem>
}