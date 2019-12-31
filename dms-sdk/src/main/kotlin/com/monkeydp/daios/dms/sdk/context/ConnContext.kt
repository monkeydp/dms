package com.monkeydp.daios.dms.sdk.context

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.tools.ext.kotlin.singleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/16
 */
interface ConnContext : Context {
    
    val cp: ConnProfile
    val datasource get() = cp.datasource
    var conn: Conn<*>
    
    companion object {
        operator fun invoke(
                cp: ConnProfile,
                init: (ConnContext.() -> Unit)? = null
        ): ConnContext = StdConnContext(cp).apply { init?.invoke(this) }
        
        operator fun invoke(map: Map<String, Any?>): ConnContext =
                this(
                        map.getValue(ConnContext::cp.name) as ConnProfile
                ) {
                    val connOrNull = map[ConnContext::conn.name] as? Conn<*>
                    if (connOrNull != null) conn = connOrNull
                }
    }
}

abstract class AbstractConnContext(
        override val cp: ConnProfile
) : ConnContext {
    override var conn: Conn<*> by Delegates.singleton()
}

private class StdConnContext(
        cp: ConnProfile
) : AbstractConnContext(cp)