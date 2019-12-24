package com.monkeydp.daios.dms.helper

import org.springframework.web.context.request.RequestContextHolder

/**
 * @author iPotato
 * @date 2019/12/25
 */
internal object DmsScopeHelper {
    val inRequestScope get() = RequestContextHolder.getRequestAttributes() != null
}