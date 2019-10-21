package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.sdk.mock.MockFactory
import com.monkeydp.daios.dms.service.contract.ConnectionService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
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
    @PostMapping("create-connection")
    fun createConnection(cp: ConnectionProfile): Long {
        return connectionService.createConnectionProfile(cp)
    }

    /**
     * @see ConnectionProfile
     */
    @PostMapping("open-connection")
    fun openConnection(cpId: Long) {
        // TODO
        connectionService.getConnectionWrapper(MockFactory.mockConnectionProfile)
    }
}