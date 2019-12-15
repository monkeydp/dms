package com.monkeydp.daios.dms.sdk.share.request

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/24
 */
object MyRequestContext {
    
    private val requestAttributesHolder = ThreadLocal<MyRequestAttributes>()
    val requestAttributes get() = requestAttributesHolder.get()!!
    
    fun setRequestAttributes(attributes: MyRequestAttributes) {
        requestAttributesHolder.set(attributes)
    }
    
    fun setRequestAttributes(init: MyRequestAttributes.() -> Unit) =
            setRequestAttributes(initInstance<MyRequestAttributes>(init))
    
    fun resetRequestAttributes() {
        requestAttributesHolder.remove()
    }
}