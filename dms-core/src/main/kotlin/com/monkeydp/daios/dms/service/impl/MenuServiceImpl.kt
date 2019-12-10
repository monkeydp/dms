package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.MenuApi
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.MenuLoadingCtx
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.daios.dms.service.contract.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class MenuServiceImpl : MenuService {
    @Lazy
    @Autowired
    lateinit var apiMap: Map<Datasource, MenuApi>
    
    override fun loadMenu(ctx: MenuLoadingCtx): Menu? {
        val ds = RequestContext.datasource
        val api = apiMap.getValue(ds)
        return api.loadMenu(ctx)
    }
}