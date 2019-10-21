package com.monkeydp.daios.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnectionFactory {
    /**
     * Get a connection
     */
    fun getConnection(profile: ConnectionProfile): Connection
}