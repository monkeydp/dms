package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnApi : Api {
    /**
     * Get a conn
     */
    fun getConn(cp: ConnProfile): Conn<*>
}