package com.monkeydp.daios.dms.sdk.share.conn

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.tools.ext.kotlin.copyPropValuesFrom
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/12/16
 */
interface ConnContext {
    var cp: ConnProfile
    val datasource get() = cp.datasource
    var conn: Conn<*>
}

fun connContext(): ConnContext = StdConnContext()

fun connContext(init: ConnContext.() -> Unit): ConnContext = initInstance<StdConnContext>(init)

fun connContext(map: Map<String, Any?>): ConnContext = connContext().apply { copyPropValuesFrom(map) }