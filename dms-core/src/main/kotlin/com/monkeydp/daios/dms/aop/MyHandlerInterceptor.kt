package com.monkeydp.daios.dms.aop

import com.monkeydp.daios.dms.request.RequestContextManager
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
class MyHandlerInterceptor : HandlerInterceptor {
    
    @Autowired
    private lateinit var manager: RequestContextManager
    
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
                                 ex: Exception?) {
        super.afterCompletion(request, response, handler, ex)
        manager.resetCtx()
    }
}