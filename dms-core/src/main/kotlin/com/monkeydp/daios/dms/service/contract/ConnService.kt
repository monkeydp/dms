package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/18
 */
interface ConnService {
    
    /**
     * Save a conn profile
     */
    fun saveCp(cp: ConnProfile): ConnProfile
    
    /**
     * Open a conn
     */
    fun openConn(cpId: Long): ConnWrapper
    
    /**
     * Close a conn
     */
    fun closeConn(cpId: Long)
    
    /**
     * Test a conn
     */
    fun testConn(cpId: Long)
    
    /**
     * Test a conn
     */
    fun testConn(cp: ConnProfile)
}