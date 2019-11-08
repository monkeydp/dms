package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.MenuLoadingCtx

/**
 * @author iPotato
 * @date 2019/11/1
 */
interface MenuService {
    fun loadMenu(ctx: MenuLoadingCtx): Menu?
}