package com.monkeydp.daios.dms.sdk.connection

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile

/**
 * Connection Factory
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnFactory {
    /**
     * Get a connection
     */
    fun getConnection(cp: ConnectionProfile): Connection
}