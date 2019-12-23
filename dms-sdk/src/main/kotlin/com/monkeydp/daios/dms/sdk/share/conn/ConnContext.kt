package com.monkeydp.daios.dms.sdk.share.conn

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.tools.ext.kotlin.copyPropValuesFrom
import com.monkeydp.tools.ext.kotlin.initInstance
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/16
 */
interface ConnContext {
    var cp: ConnProfile
    val datasource get() = cp.datasource
    var conn: Conn<*>
    
    companion object {
        operator fun invoke(init: (ConnContext.() -> Unit)? = null): ConnContext = initInstance<StdConnContext>(init)
        operator fun invoke(map: Map<String, Any?>): ConnContext = this().apply { copyPropValuesFrom(map) }
    }
}

abstract class AbstractConnContext : ConnContext {
    override var cp: ConnProfile by Delegates.notNullSingleton()
    override var conn: Conn<*> by Delegates.notNullSingleton()
}

private class StdConnContext : AbstractConnContext()