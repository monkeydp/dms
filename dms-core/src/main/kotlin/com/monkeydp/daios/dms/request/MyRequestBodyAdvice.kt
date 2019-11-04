package com.monkeydp.daios.dms.request

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.dsThreadLocal
import com.monkeydp.daios.dms.service.contract.ConnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter
import java.io.PushbackInputStream
import java.lang.reflect.Type

/**
 * @author iPotato
 * @date 2019/11/3
 */
@RestControllerAdvice
class MyRequestBodyAdvice : RequestBodyAdviceAdapter() {
    
    private val mapper = jacksonObjectMapper()
    
    @Autowired
    private lateinit var connService: ConnService
    
    override fun supports(methodParameter: MethodParameter, targetType: Type,
                          converterType: Class<out HttpMessageConverter<*>>) = true
    
    override fun beforeBodyRead(inputMessage: HttpInputMessage, methodParameter: MethodParameter, targetType: Type,
                                selectedConverterType: Class<out HttpMessageConverter<*>>): HttpInputMessage {
        if (dsThreadLocal.get() != null) return inputMessage
        val annot = (targetType as Class<*>).getDeclaredAnnotation(NeedDatasource::class.java)
        if (annot == null) return inputMessage
        val body = inputMessage.body as PushbackInputStream
        val bytes = body.readBytes()
        val datasource = getDatasource(bytes, annot)
        dsThreadLocal.set(datasource)
        return MyHttpInputMessage(inputMessage.headers, bytes.inputStream())
    }
    
    private fun getDatasource(bytes: ByteArray, annot: NeedDatasource): Datasource {
        val map: MutableMap<String, Any> = mapper.readValue(bytes)
        val cpId = (map.getValue(annot.cpIdName) as Int).toLong()
        return connService.findCp(cpId).datasource
    }
    
    override fun afterBodyRead(body: Any, inputMessage: HttpInputMessage, parameter: MethodParameter, targetType: Type,
                               converterType: Class<out HttpMessageConverter<*>>): Any {
        dsThreadLocal.remove()
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType)
    }
}