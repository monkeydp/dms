package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.ui.form.FormLoadingCtx
import com.monkeydp.daios.dms.service.contract.FormService
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
@Api(tags = ["Form"])
@RestController
@RequestMapping("form")
class FormController @Autowired constructor(
        private val service: FormService
) {
    @ApiOperation("Load form")
    @PostMapping("load-form")
    fun loadForm(@RequestBody ctx: FormLoadingCtx) = service.loadForm(ctx.instr).items
}