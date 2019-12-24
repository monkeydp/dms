package com.monkeydp.daios.dms.sdk.helper

import com.monkeydp.daios.dms.sdk.share.request.RequestContextHolder

/**
 * @author iPotato
 * @date 2019/12/25
 */
object ScopeHelper {
    val inRequestScope get() = RequestContextHolder.requestAttributesOrNull != null
}