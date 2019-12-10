package com.monkeydp.daios.dms.sdk.request

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/11/24
 */
object RequestContext {
    private val cpThreadLocal = ThreadLocal<ConnProfile?>()
    
    private val cpOrNull get() = cpThreadLocal.get()
    val cp get() = cpOrNull!!
    
    private val cpIdOrNull get() = cpOrNull?.id
    val cpId get() = cpIdOrNull!!
    
    private val datasourceOrNull get() = cpOrNull?.datasource
    val datasource get() = datasourceOrNull!!
    
    private val connThreadLocal = ThreadLocal<Conn<*>?>()
    private val connOrNull get() = connThreadLocal.get()
    val conn get() = connOrNull!!
    
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