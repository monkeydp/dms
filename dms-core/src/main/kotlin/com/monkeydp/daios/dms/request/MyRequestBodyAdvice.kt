package com.monkeydp.daios.dms.request

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter
import java.lang.reflect.Type

/**
 * @author iPotato
 * @date 2019/11/3
 */
@RestControllerAdvice
class MyRequestBodyAdvice : RequestBodyAdviceAdapter() {
    
    @Autowired
    private lateinit var manager: RequestContextHolderManager
    
    override fun supports(methodParameter: MethodParameter, targetType: Type,
                          converterType: Class<out HttpMessageConverter<*>>) = true
    
    override fun beforeBodyRead(inputMessage: HttpInputMessage, methodParameter: MethodParameter, targetType: Type,
                                selectedConverterType: Class<out HttpMessageConverter<*>>): HttpInputMessage {
        val body = inputMessage.body
        val data = body.readBytes()
        manager.initHolder(targetType, data, methodParameter)
        return MyHttpInputMessage(inputMessage.headers, data.inputStream())
    }
}