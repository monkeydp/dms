package com.monkeydp.daios.dms.sdk.request

import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/11/24
 */
object RequestContext {
    val cpThreadLocal = ThreadLocal<ConnProfile>()
    val cp
        get() = cpThreadLocal.get()
    val datasource
        get() = cp.datasource
}