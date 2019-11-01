package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.ctx.NodeMenuLoadCtxForm

/**
 * @author iPotato
 * @date 2019/11/1
 */
interface MenuService {
    fun loadNodeMenu(ctx: NodeMenuLoadCtxForm): Menu?
}