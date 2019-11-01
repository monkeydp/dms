package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.ctx.NodeMenuLoadCtxForm
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class MenuServiceImpl : MenuService {
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var connService: ConnService
    
    override fun loadNodeMenu(ctx: NodeMenuLoadCtxForm): Menu? {
        val cp = connService.findCp(ctx.cpId)
        val bundle = registry.getBundle(cp)
        return bundle.apis.menuApi.loadNodeMenu(ctx.toInner())
    }
}