package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.MenuApi
import com.monkeydp.daios.dms.sdk.metadata.menu.MenuLoadingCtx
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodeinHelper
import com.monkeydp.daios.dms.service.contract.MenuService
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class MenuServiceImpl : MenuService {
    
    private val api: MenuApi get() = DmKodeinHelper.findImpl()
    
    override fun loadMenu(ctx: MenuLoadingCtx) = api.loadMenu(ctx)
}