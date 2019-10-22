package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/18
 */
interface ConnectionService {

    /**
     * Create a ConnectionProfile
     */
    fun createConnectionProfile(cp: ConnectionProfile): Long

    /**
     * Get a ConnectionWrapper
     * @see ConnectionWrapper
     */
    fun getConnectionWrapper(cp: ConnectionProfile): ConnectionWrapper
}