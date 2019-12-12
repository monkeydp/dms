package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.MenuLoadingCtx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface MenuApi {
    fun loadMenu(ctx: MenuLoadingCtx): Menu?
}