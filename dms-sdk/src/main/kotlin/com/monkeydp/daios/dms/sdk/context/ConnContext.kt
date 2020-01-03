package com.monkeydp.daios.dms.sdk.context

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/12/16
 */
class ConnContext(
        val cp: ConnProfile,
        conn: Conn<*>? = null
) {
    val datasource get() = cp.datasource
    private val _conn: Conn<*>? = conn
    val conn: Conn<*> get() = _conn!!
}