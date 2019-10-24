package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/18
 */
interface ConnectionService {
    
    /**
     * Save a cp
     */
    fun saveConnectionProfile(cp: ConnectionProfile): ConnectionProfile
    
    /**
     * Open a connection
     */
    fun openConnection(cpId: Long): ConnectionWrapper
    
    /**
     * Close a connection
     */
    fun closeConnection(cpId: Long)
    
    /**
     * Test a connection
     */
    fun testConnection(cpId: Long)
}