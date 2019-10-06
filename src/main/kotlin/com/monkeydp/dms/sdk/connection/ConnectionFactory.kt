package com.monkeydp.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnectionFactory {
    /**
     * Create a connection
     */
    fun newConnection(profile: ConnectionProfile): Connection
}