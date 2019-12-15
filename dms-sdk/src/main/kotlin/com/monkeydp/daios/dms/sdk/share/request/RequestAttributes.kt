package com.monkeydp.daios.dms.sdk.share.request

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/15
 */
interface RequestAttributes {
    var cp: ConnProfile
    val datasource get() = cp.datasource
    var conn: Conn<*>
}