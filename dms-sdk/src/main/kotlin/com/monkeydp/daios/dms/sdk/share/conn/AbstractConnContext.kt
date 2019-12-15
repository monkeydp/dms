package com.monkeydp.daios.dms.sdk.share.conn

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/16
 */
abstract class AbstractConnContext : ConnContext {
    override var cp: ConnProfile by Delegates.notNullSingleton()
    override var conn: Conn<*> by Delegates.notNullSingleton()
}