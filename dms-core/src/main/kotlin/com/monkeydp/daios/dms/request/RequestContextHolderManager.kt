package com.monkeydp.daios.dms.request

import com.monkeydp.daios.dms.sdk.conn.ConnRequired
import com.monkeydp.daios.dms.sdk.conn.HasCpId
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.share.request.RequestContextHolder
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.tools.util.JsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import java.lang.reflect.Type
import kotlin.reflect.full.isSuperclassOf

/**
 * @author iPotato
 * @date 2019/11/24
 */
@Component
class RequestContextHolderManager {
    
    @Autowired
    private lateinit var connService: ConnService
    
    fun initHolder(type: Type, data: ByteArray, methodParameter: MethodParameter) {
        val cpId = getCpId(type, methodParameter.parameterName, data)
        if (cpId == null) return
        val connOrNull =
                if (methodParameter.method!!.isAnnotationPresent(ConnRequired::class.java))
                    connService.findConn(cpId)
                else null
        RequestContextHolder.setRequestAttributes(
                ConnContext(connService.findCp(cpId)) {
                    if (connOrNull != null)
                        conn = connOrNull
                }
        )
    }
    
    private fun getCpId(type: Type, name: String?, data: ByteArray): Long? {
        
        val kClass = (type as Class<*>).kotlin
        val cpIdName = HasCpId::cpId.name
        
        if (kClass.isSuperclassOf(Long::class) && name == cpIdName)
            return JsonUtil.toJsonNode(data).longValue()
        
        if (!HasCpId::class.isSuperclassOf(kClass)) return null
        val jsonNode = JsonUtil.toJsonNode(data)
        return jsonNode.get(cpIdName).asLong()
    }
    
    fun resetHolder() {
        RequestContextHolder.resetRequestAttributes()
    }
}