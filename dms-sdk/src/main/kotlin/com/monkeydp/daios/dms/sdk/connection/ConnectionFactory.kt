package com.monkeydp.daios.dms.sdk.connection

import com.monkeydp.daios.dms.sdk.model.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnectionFactory {
    /**
     * Get a connection
     */
    fun getConnection(cp: ConnectionProfile): Connection
}