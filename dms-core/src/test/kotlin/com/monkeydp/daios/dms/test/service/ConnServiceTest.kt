package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.module.ModuleTestdata
import com.monkeydp.daios.dms.service.contract.ConnService
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnServiceTest : AbstractServiceTest() {
    
    @Autowired
    private lateinit var service: ConnService
    
    private val cp get() =  ModuleTestdata.cp
    
    @Test
    public fun saveCpTest() {
        val cp = service.saveCp(cp)
        assertTrue(cp.isValid())
    }
    
    /**
     * test for following method
     * @see ConnService.openConn
     * @see ConnService.closeConn
     */
    @Test
    public fun connTest() {
        val cw = service.openConn(cp.id)
        val conn = cw.conn
        
        assertTrue(conn.isValid())
        assertFalse(conn.isClosed())
        
        service.closeConn(cp.id)
        assertFalse(conn.isValid())
        assertTrue(conn.isClosed())
    }
    
    @Test
    public fun testConnTest() {
        service.testConn(cp.id)
    }
}