package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.conn.BelongsTo
import com.monkeydp.daios.dms.conn.BelongsTo.USER
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

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
    fun openConn(cpId: Long, belongsTo: BelongsTo = USER): ConnWrapper
    
    /**
     * Close a conn
     * @param connId if null, close user conn
     */
    fun closeConn(cpId: Long, connId: Long? = null)
    
    /**
     * Test a conn
     */
    fun testConn(cpId: Long)
    
    /**
     * Test a conn
     */
    fun testConn(cp: ConnProfile)
    
    /**
     * Find a conn profile
     * @see ConnManger from conn manager first, then is db
     */
    fun findCp(cpId: Long): ConnProfile
    
    /**
     * Find a conn wrapper
     * @param connId if null, return user conn wrapper
     */
    fun findCw(cpId: Long, connId: Long? = null): ConnWrapper
    
    /**
     * Find a conn
     * @param connId if null, return user conn
     */
    fun findConn(cpId: Long, connId: Long? = null) = findCw(cpId, connId).conn
}