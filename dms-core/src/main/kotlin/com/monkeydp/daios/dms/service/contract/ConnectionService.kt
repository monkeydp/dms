package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/18
 */
interface ConnectionService {
    
    /**
     * Save a connection profile
     */
    fun saveCp(cp: ConnectionProfile): ConnectionProfile
    
    /**
     * Open a connection
     */
    fun openConn(cpId: Long): ConnectionWrapper
    
    /**
     * Close a connection
     */
    fun closeConn(cpId: Long)
    
    /**
     * Test a connection
     */
    fun testConn(cpId: Long)
    
    /**
     * Test a connection
     */
    fun testConn(cp: ConnectionProfile)
}