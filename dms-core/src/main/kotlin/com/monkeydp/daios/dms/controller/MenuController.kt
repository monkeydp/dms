package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.metadata.menu.MenuLoadingCtx
import com.monkeydp.daios.dms.service.contract.MenuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Api(tags = ["Menu"])
@RestController
@RequestMapping("menu")
class MenuController {
    
    @Autowired
    private lateinit var service: MenuService
    
    @ApiOperation("Load menu")
    @PostMapping("load-menu")
    fun loadMenu(@RequestBody ctx: MenuLoadingCtx) = service.loadMenu(ctx)?.items ?: emptyList()
}