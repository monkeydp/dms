package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.service.contract.DispatcherService
import com.monkeydp.daios.dms.test.BaseTest
import com.monkeydp.daios.dms.test.mock.MockFactory.mockConnectionProfile
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/18
 */
class DispatcherServiceTest : BaseTest() {
    @Autowired
    private lateinit var dispatcherService: DispatcherService

    @Test
    public fun getConnectionTest() {
        dispatcherService.getConnection(mockConnectionProfile)
    }
}