package com.monkeydp.daios.dms.request

import com.monkeydp.daios.dms.component.ContextManager
import com.monkeydp.daios.dms.controller.ContextController
import com.monkeydp.daios.dms.sdk.ui.context.UiConnContext
import com.monkeydp.daios.dms.sdk.ui.context.UiContextRepo
import com.monkeydp.daios.dms.service.ContextService
import com.monkeydp.tools.util.JsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter
import java.io.InputStream
import java.lang.reflect.Type
import kotlin.reflect.jvm.javaMethod

/**
 * @author iPotato
 * @date 2019/11/3
 */
@RestControllerAdvice
class MyRequestBodyAdvice @Autowired constructor(
        private val contextService: ContextService,
        private val manager: ContextManager
) : RequestBodyAdviceAdapter() {
    
    override fun supports(methodParameter: MethodParameter, targetType: Type,
                          converterType: Class<out HttpMessageConverter<*>>) = true
    
    override fun beforeBodyRead(
            inputMessage: HttpInputMessage,
            parameter: MethodParameter,
            targetType: Type,
            converterType: Class<out HttpMessageConverter<*>>
    ): HttpInputMessage {
        if (parameter.method != ContextController::saveRepo.javaMethod)
            return super.beforeBodyRead(inputMessage, parameter, targetType, converterType)
        
        val data = inputMessage.body.readBytes()
        saveConnContextFirst(data)
        return MyHttpInputMessage(inputMessage.headers, data.inputStream())
    }
    
    /**
     * Because json parsing of other context needs datasource
     */
    private fun saveConnContextFirst(data: ByteArray) {
        val cpId = JsonUtil.toJsonNode(data)
                .get(UiContextRepo::connContext.name)
                .get(UiConnContext::cpId.name).longValue()
        contextService.saveRepo {
            connContext = UiConnContext(cpId)
        }
        manager.initContextHolder()
    }
}

private class MyHttpInputMessage(
        @get:JvmName("getHeaders_") val headers: HttpHeaders,
        @get:JvmName("getBody_") val body: InputStream
) : HttpInputMessage {
    override fun getHeaders() = headers
    override fun getBody() = body
}