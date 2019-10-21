package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.sdk.mock.MockFactory
import com.monkeydp.daios.dms.service.contract.ConnectionService
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
     */
    @PostMapping("open-connection")
    fun openConnection(connProfileId: Long) {
        // TODO
        connectionService.connectionWrapper(MockFactory.mockConnectionProfile)
    }
}