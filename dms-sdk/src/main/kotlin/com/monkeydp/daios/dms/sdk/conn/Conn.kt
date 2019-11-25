package com.monkeydp.daios.dms.sdk.conn

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface Conn<C> : AutoCloseable {
    
    var id: Long
    
    /**
     * @see ConnProfile.id
     */
    val cpId: Long
    
    /**
     * Raw conn of database
     */
    val rawConn: C
    
    /**
     * Is conn valid
     * @param timeout second
     */
    fun isValid(timeout: Int = 10): Boolean
    
    /**
     * Close conn
     */
    override fun close()
    
    /**
     * Is conn closed
     */
    fun isClosed(): Boolean
}