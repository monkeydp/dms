package com.monkeydp.daios.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnectionFactory {
    /**
     * Create a connection
     */
    fun connection(profile: ConnectionProfile): Connection
}