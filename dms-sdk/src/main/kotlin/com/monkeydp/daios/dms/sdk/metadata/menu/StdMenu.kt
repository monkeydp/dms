package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/11/1
 */
data class StdMenu(
        override val items: List<MenuItem> = emptyList()
) : Menu