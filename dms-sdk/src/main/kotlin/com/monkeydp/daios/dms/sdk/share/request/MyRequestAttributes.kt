package com.monkeydp.daios.dms.sdk.share.request

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/15
 */
class MyRequestAttributes {
    var cp: ConnProfile by Delegates.notNullSingleton()
    val datasource get() = cp.datasource
    var conn: Conn<*> by Delegates.notNullSingleton()
}