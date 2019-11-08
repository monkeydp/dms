package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import com.monkeydp.daios.dms.service.contract.ConnService
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
 * @date 2019/10/21
 */
@Api(tags = ["Connection"])
@RestController
@RequestMapping("conn")
class ConnController {
    
    @Autowired
    private lateinit var service: ConnService
    
    @ApiOperation("Save connection profile")
    @PostMapping("save-cp")
    fun saveCp(@RequestBody cp: ConnProfile) = service.saveCp(cp)
    
    @ApiOperation("Open connection")
    @PostMapping("open-conn")
    fun openConn(@RequestBody @ApiParam(example = CP_ID) cpId: Long) {
        service.openConn(cpId)
    }
    
    @ApiOperation("Close connection")
    @PostMapping("close-conn")
    fun closeConn(@RequestBody @ApiParam(example = CP_ID) cpId: Long) {
        service.closeConn(cpId)
    }
    
    @ApiOperation("Test connection")
    @PostMapping("test-conn")
    fun testConn(@RequestBody @ApiParam(example = CP_ID) cpId: Long) {
        service.testConn(cpId)
    }
}