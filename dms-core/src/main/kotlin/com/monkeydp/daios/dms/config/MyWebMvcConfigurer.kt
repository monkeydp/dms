package com.monkeydp.daios.dms.config

import com.monkeydp.daios.dms.aop.MyHandlerInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * @author iPotato
 * @date 2019/11/24
 */
@Configuration
class MyWebMvcConfigurer : WebMvcConfigurer {
    
    @Autowired
    private lateinit var interceptor: MyHandlerInterceptor
    
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
    }
}