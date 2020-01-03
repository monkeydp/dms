package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.config.kodein
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.context.ConnContext
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.tools.ext.kodein.providerX
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
class ConnController @Autowired constructor(
        private val service: ConnService
) {
    private val connContext: ConnContext get() = kodein.providerX()
    
    @ApiOperation("Save connection profile")
    @PostMapping("save-cp")
    fun saveCp(@RequestBody cp: ConnProfile) = service.saveCp(cp)
    
    @ApiOperation("Open connection")
    @PostMapping("open-conn")
    fun openConn() {
        service.openConn(connContext.cp.id)
    }
    
    @ApiOperation("Close connection")
    @PostMapping("close-conn")
    fun closeConn() {
        service.closeConn(connContext.cp.id)
    }
    
    @ApiOperation("Test connection")
    @PostMapping("test-conn")
    fun testConn() {
        service.testConn(connContext.cp.id)
    }
}