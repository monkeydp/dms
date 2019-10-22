package com.monkeydp.daios.dms.test.curd.service

import com.monkeydp.daios.dms.curd.service.contract.ConnectionProfileService
import com.monkeydp.daios.dms.sdk.mock.MockFactory
import com.monkeydp.daios.dms.test.BaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/22
 */
class ConnectionProfileServiceTest : BaseTest() {

    @Autowired
    private lateinit var service: ConnectionProfileService

    @Test
    fun saveTest() {
        service.save(MockFactory.mockConnectionProfile)
    }
}