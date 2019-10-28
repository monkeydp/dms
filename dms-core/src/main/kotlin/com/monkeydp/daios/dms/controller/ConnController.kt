package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.service.contract.ConnService
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
@RestController
@RequestMapping("conn")
class ConnController {
    
    @Autowired
    private lateinit var service: ConnService
    
    @PostMapping("save-cp")
    fun saveCp(@RequestBody cp: ConnProfile) = service.saveCp(cp)
    
    @PostMapping("open-conn")
    fun openConn(@RequestBody @ApiParam(example = "1") cpId: Long) {
        service.openConn(cpId)
    }
    
    @PostMapping("close-conn")
    fun closeConn(@RequestBody @ApiParam(example = "1") cpId: Long) {
        service.closeConn(cpId)
    }
    
    @PostMapping("test-conn")
    fun testConn(@RequestBody @ApiParam(example = "1") cpId: Long) {
        service.testConn(cpId)
    }
}