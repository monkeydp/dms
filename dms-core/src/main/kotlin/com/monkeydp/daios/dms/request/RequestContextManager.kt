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
class RequestContextManager {
    
    private val ctx = RequestContext
    
    @Autowired
    private lateinit var connService: ConnService
    
    fun initCtx(type: Type, data: ByteArray) {
        val cpId = getCpId(type, data)
        if (cpId != null) {
            val cp = connService.findCp(cpId)
            val conn = connService.findConn(cpId)
            ctx.init(cp, conn)
        }
    }
    
    private fun getCpId(type: Type, data: ByteArray): Long? {
        val kClass = (type as Class<*>).kotlin
        if (!HasCpId::class.isSuperclassOf(kClass)) return null
        val jsonNode = JsonUtil.toJsonNode(data)
        return jsonNode.get(HasCpId::cpId.name).asLong()
    }
    
    fun cleanCtx() {
        ctx.clean()
    }
}