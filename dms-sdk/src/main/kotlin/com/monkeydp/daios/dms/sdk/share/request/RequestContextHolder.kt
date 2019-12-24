package com.monkeydp.daios.dms.sdk.share.request

/**
 * Holder class to expose the web request in the form of a thread-bound
 *
 * @author iPotato
 * @date 2019/12/15
 */
interface RequestContextHolder {
    companion object {
        private val requestAttributesHolder = ThreadLocal<RequestAttributes?>()
        val requestAttributesOrNull: RequestAttributes? get() = requestAttributesHolder.get()
        val requestAttributes: RequestAttributes get() = requestAttributesOrNull!!
        
        fun setRequestAttributes(attributes: RequestAttributes) {
            requestAttributesHolder.set(attributes)
        }
        
        fun setRequestAttributes(any: Any) {
            setRequestAttributes(RequestAttributes(any))
        }
        
        fun resetRequestAttributes() {
            requestAttributesHolder.remove()
        }
    }
}