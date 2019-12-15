package com.monkeydp.daios.dms.sdk.share.request

/**
 * @author iPotato
 * @date 2019/12/15
 */
interface RequestAttributes {
    val attrs: Map<String, Any?>
    fun <T : Any> getAttribute(name: String): T
    fun setAttribute(name: String, value: Any?)
    fun setAttributes(map: Map<String, Any?>)
}

fun requestAttributes(any: Any? = null): RequestAttributes = StdRequestAttributes(any)