package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
import com.monkeydp.daios.dms.service.contract.ConnectionService
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
@RequestMapping("connection")
class ConnectionController {

    @Autowired
    lateinit var connectionService: ConnectionService

    /**
     * @see ConnectionProfile
     * @return ConnectionProfile.id
     */
    @PostMapping("create-cp")
    fun createCp(@RequestBody cp: ConnectionProfile) = connectionService.saveConnectionProfile(cp)

    /**
     * @see ConnectionProfile
     */
    @PostMapping("open-connection")
    fun openConnection(cpId: Long) {
        connectionService.openConnection(cpId)
    }
}