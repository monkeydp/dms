package com.monkeydp.daios.dms.sdk.share.request

/**
 * @author iPotato
 * @date 2019/12/15
 */
interface RequestContext {
    val requestAttributes: RequestAttributes
    fun setRequestAttributes(attributes: RequestAttributes)
    fun setRequestAttributes(any: Any)
    fun resetRequestAttributes()
    
    companion object {
        final val SINGLETON: RequestContext = StdRequestContext
    }
}

internal abstract class AbstractRequestContext : RequestContext {
    
    private val requestAttributesHolder = ThreadLocal<RequestAttributes>()
    override val requestAttributes get() = requestAttributesHolder.get()!!
    
    override fun setRequestAttributes(attributes: RequestAttributes) {
        requestAttributesHolder.set(attributes)
    }
    
    override fun setRequestAttributes(any: Any) {
        setRequestAttributes(RequestAttributes(any))
    }
    
    override fun resetRequestAttributes() {
        requestAttributesHolder.remove()
    }
}

private object StdRequestContext : AbstractRequestContext()