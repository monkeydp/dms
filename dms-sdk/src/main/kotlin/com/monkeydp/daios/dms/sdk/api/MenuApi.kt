package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.MenuLoadCtx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface MenuApi {
    fun loadMenu(ctx: MenuLoadCtx): Menu?
}