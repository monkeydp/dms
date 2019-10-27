package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * Conn Factory
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnFactory {
    /**
     * Get a conn
     */
    fun getConn(cp: ConnProfile): Conn
}