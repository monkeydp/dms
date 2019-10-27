package com.monkeydp.daios.dms.sdk.conn

/**
 * @author iPotato
 * @date 2019/10/21
 */
interface LogicConn : AutoCloseable {
    
    /**
     * Raw conn of database
     */
    val rawConn: Any
    
    /**
     * Is conn valid
     * @param timeout second
     */
    fun isValid(timeout: Int): Boolean
    
    /**
     * Close conn
     */
    override fun close()
    
    /**
     * Is conn closed
     */
    fun isClosed(): Boolean
}