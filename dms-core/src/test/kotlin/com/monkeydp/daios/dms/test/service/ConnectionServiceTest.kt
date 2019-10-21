package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.sdk.mock.MockFactory.mockConnectionProfile
import com.monkeydp.daios.dms.service.contract.ConnectionService
import com.monkeydp.daios.dms.test.BaseTest
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnectionServiceTest : BaseTest() {

    @Autowired
    private lateinit var connectionService: ConnectionService

    @Test
    public fun connectionTest() {
        val wrapper = connectionService.getConnectionWrapper(mockConnectionProfile)
        val connection = wrapper.connection

        Assert.assertTrue(connection.isValid())
        Assert.assertFalse(connection.isClosed())

        connection.close()
        Assert.assertFalse(connection.isValid())
        Assert.assertTrue(connection.isClosed())
    }
}