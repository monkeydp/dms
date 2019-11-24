package com.monkeydp.daios.dms.sdk.request

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/11/24
 */
object RequestContext {
    private val cpThreadLocal = ThreadLocal<ConnProfile?>()
    val cp
        get() = cpThreadLocal.get()
    val cpId
        get() = cp?.id
    val datasource
        get() = cp?.datasource
    
    private val connThreadLocal = ThreadLocal<Conn<*>?>()
    val conn
        get() = connThreadLocal.get()
    
    fun init(cp: ConnProfile? = null, conn: Conn<*>? = null) {
        setCp(cp)
        setConn(conn)
    }
    
    private fun setCp(cp: ConnProfile?) {
        cpThreadLocal.set(cp)
    }
    
    private fun setConn(conn: Conn<*>?) {
        connThreadLocal.set(conn)
    }
    
    fun clean() {
        removeCp()
        removeConn()
    }
    
    private fun removeCp() {
        cpThreadLocal.remove()
    }
    
    private fun removeConn() {
        connThreadLocal.remove()
    }
}