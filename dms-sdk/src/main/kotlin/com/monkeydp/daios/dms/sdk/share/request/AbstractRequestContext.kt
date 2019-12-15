package com.monkeydp.daios.dms.sdk.share.request

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/12/15
 */
internal abstract class AbstractRequestContext : RequestContext {
    private val requestAttributesHolder = ThreadLocal<RequestAttributes>()
    override val requestAttributes get() = requestAttributesHolder.get()!!
    
    override fun setRequestAttributes(attributes: RequestAttributes) {
        requestAttributesHolder.set(attributes)
    }
    
    override fun setRequestAttributes(init: RequestAttributes.() -> Unit) =
            setRequestAttributes(initInstance<MyRequestAttributes>(init))
    
    override fun resetRequestAttributes() {
        requestAttributesHolder.remove()
    }
}