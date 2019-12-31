package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.ui.context.UiContextRepo
import com.monkeydp.daios.dms.service.ContextService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author iPotato
 * @date 2019/12/30
 */
@Api(tags = ["Context"])
@RestController
@RequestMapping("context")
class ContextController @Autowired constructor(
        private val service: ContextService
) {
    @ApiOperation("Save context repository")
    @PostMapping("save-repo")
    fun saveRepo(@RequestBody repo: UiContextRepo) = service.saveRepo(repo)
}