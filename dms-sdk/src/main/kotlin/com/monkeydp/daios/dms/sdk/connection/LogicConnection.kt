package com.monkeydp.daios.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/21
 */
interface LogicConnection {

    /**
     * Raw connection of database
     */
    val rawConnection: Any

    /**
     * Is connection valid
     * @param timeout second
     */
    fun isValid(timeout: Int): Boolean

    /**
     * Close connection
     */
    fun close()

    /**
     * Is connection closed
     */
    fun isClosed(): Boolean
}