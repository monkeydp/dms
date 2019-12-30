package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.ui.menu.Menu

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface MenuApi : Api {
    fun loadMenu(menuDefId: Int): Menu
}