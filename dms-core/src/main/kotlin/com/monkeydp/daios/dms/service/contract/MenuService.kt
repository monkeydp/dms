package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu

/**
 * @author iPotato
 * @date 2019/11/1
 */
interface MenuService {
    fun loadMenu(menuDefId: Int): Menu
}