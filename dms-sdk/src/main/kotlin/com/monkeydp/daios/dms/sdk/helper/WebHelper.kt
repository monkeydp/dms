package com.monkeydp.daios.dms.sdk.helper

/**
 * @author iPotato
 * @date 2020/4/18
 */
object WebHelper {
    private val inRequestScopeTL = ThreadLocal<Boolean>()
    val inRequestScope get() = inRequestScopeTL.get()
    
    fun setRequestScope() {
        inRequestScopeTL.set(true)
    }
    
    fun resetRequestScope() {
        inRequestScopeTL.remove()
    }
}