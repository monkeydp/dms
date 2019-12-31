package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker
import com.monkeydp.daios.dms.service.contract.FormService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
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
    fun loadForm(
            @RequestBody
            @ApiParam(required = true, example = InstrJsonMocker.INSTR)
            instr: Instruction
    ) = service.loadForm(instr).items
}