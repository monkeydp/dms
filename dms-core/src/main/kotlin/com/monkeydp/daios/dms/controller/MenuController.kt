package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.metadata.menu.ctx.NodeMenuLoadCtxForm
import com.monkeydp.daios.dms.service.contract.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author iPotato
 * @date 2019/10/28
 */
@RestController
@RequestMapping("menu")
class MenuController {
    
    @Autowired
    private lateinit var service: MenuService
    
    @PostMapping("load-node-menu")
    fun loadNodeMenu(@RequestBody ctx: NodeMenuLoadCtxForm) = service.loadNodeMenu(ctx)
}