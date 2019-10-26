package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.sdk.connection.CpMocker.testCp
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
    private lateinit var service: ConnectionService
    
    
    @Test
    public fun saveCpTest() {
        val cp = service.saveCp(testCp())
        Assert.assertTrue(cp.isValid())
    }
    
    /**
     * test for following method
     * @see ConnectionService.openConn
     * @see ConnectionService.closeConn
     */
    @Test
    public fun connTest() {
        val wrapper = service.openConn(testCp().id)
        val connection = wrapper.connection
        
        Assert.assertTrue(connection.isValid())
        Assert.assertFalse(connection.isClosed())
        
        service.closeConn(testCp().id)
        Assert.assertFalse(connection.isValid())
        Assert.assertTrue(connection.isClosed())
    }
    
    @Test
    public fun testConnTest() {
        service.testConn(testCp().id)
    }
}