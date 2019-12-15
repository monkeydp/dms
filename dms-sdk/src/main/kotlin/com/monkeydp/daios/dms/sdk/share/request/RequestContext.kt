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
}

internal val requestContext = StdRequestContext