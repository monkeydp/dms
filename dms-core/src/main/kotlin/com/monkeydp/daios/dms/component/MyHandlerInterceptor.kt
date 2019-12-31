package com.monkeydp.daios.dms.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author iPotato
 * @date 2019/11/24
 */
@Component
class MyHandlerInterceptor @Autowired constructor(
        private val manager: ContextManager
) : HandlerInterceptor {
    
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        manager.initContextHolder()
        return super.preHandle(request, response, handler)
    }
    
    override fun afterCompletion(
            request: HttpServletRequest,
            response: HttpServletResponse,
            handler: Any,
            ex: Exception?
    ) {
        super.afterCompletion(request, response, handler, ex)
        manager.resetContextHolder()
    }
}