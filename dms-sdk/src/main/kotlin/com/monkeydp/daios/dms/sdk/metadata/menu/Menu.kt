package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface Menu {
    val items: List<MenuItem>
    
    companion object {
        operator fun invoke(items: List<MenuItem>): Menu = StdMenu(items)
    }
}

abstract class AbstractMenu(override val items: List<MenuItem> = emptyList()) : Menu

private class StdMenu(items: List<MenuItem> = emptyList()) : AbstractMenu(items)