package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.NodeInstrParseCtxForm
import com.monkeydp.daios.dms.service.contract.InstrService
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
@Api(tags = ["Instruction"])
@RestController
@RequestMapping("instr")
class InstrController {
    
    @Autowired
    private lateinit var service: InstrService
    
    @ApiOperation("Parse instruction related to node")
    @PostMapping("parse-node-instr")
    fun parseNodeInstr(@RequestBody form: NodeInstrParseCtxForm) = service.parse(form)
}