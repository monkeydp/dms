package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.dm.DmTestdataRegistry.testCp
import com.monkeydp.daios.dms.service.contract.ConnService
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnServiceTest : AbstractServiceTest() {
    
    @Autowired
    private lateinit var service: ConnService
    
    @Test
    public fun saveCpTest() {
        val cp = service.saveCp(testCp())
        Assert.assertTrue(cp.isValid())
    }
    
    /**
     * test for following method
     * @see ConnService.openConn
     * @see ConnService.closeConn
     */
    @Test
    public fun connTest() {
        val cw = service.openConn(testCp().id)
        val conn = cw.conn
    
        Assert.assertTrue(conn.isValid())
        Assert.assertFalse(conn.isClosed())
        
        service.closeConn(testCp().id)
        Assert.assertFalse(conn.isValid())
        Assert.assertTrue(conn.isClosed())
    }
    
    @Test
    public fun testConnTest() {
        service.testConn(testCp().id)
    }
}