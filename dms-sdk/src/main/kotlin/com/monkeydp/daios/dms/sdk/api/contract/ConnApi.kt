package com.monkeydp.daios.dms.sdk.api.contract

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/6
 */
interface ConnApi {
    /**
     * Get a conn
     */
    fun getConn(cp: ConnProfile): Conn<*>
}