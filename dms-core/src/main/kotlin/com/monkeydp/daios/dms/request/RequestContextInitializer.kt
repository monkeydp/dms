package com.monkeydp.daios.dms.request

import com.monkeydp.daios.dms.sdk.conn.HasCpId
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.tools.util.JsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.reflect.Type
import kotlin.reflect.full.isSuperclassOf

/**
 * @author iPotato
 * @date 2019/11/24
 */
@Component
class RequestContextInitializer {
    
    @Autowired
    private lateinit var connService: ConnService
    
    fun initCtx(type: Type, data: ByteArray) {
        initCpThreadLocal(type, data)
    }
    
    private fun initCpThreadLocal(type: Type, data: ByteArray) {
        val kClass = (type as Class<*>).kotlin
        if (!HasCpId::class.isSuperclassOf(kClass)) return
        val jsonNode = JsonUtil.toJsonNode(data)
        val cpId = jsonNode.get(HasCpId::cpId.name).asLong()
        val cp = connService.findCp(cpId)
        RequestContext.cpThreadLocal.set(cp)
    }
    
    fun cleanCtx() {
        cleanCpThreadLocal()
    }
    
    private fun cleanCpThreadLocal() {
        RequestContext.cpThreadLocal.remove()
    }
}