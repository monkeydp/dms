package com.monkeydp.daios.dms.connection

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
import com.monkeydp.tools.exception.inner.StdInnerException
import com.monkeydp.tools.ierror
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/24
 */
@Component
class ConnectionManager {
    /**
     * @see ConnectionProfile.id
     */
    private val activeCpwMap = mutableMapOf<Long, ConnectionProfileWrapper>()
    
    /**
     * Regularly clean up expired connections
     */
    private fun autoClean() {
        // TODO
    }
    
    fun activateCp(cp: ConnectionProfile): ConnectionManager {
        val cpw = ConnectionProfileWrapper(cp)
        activeCpwMap.putIfAbsent(cpw.cpId, cpw)
        return this
    }
    
    @Synchronized
    fun activateConn(cw: ConnectionWrapper): ConnectionManager {
        val cpw = activeCpwMap[cw.cpId]!!
        if (cw.belongsToUser() && cpw.hasActiveUserConn)
            throw StdInnerException("Active user connection is already exist!")
        cpw.activeCwMap.putIfAbsent(cw.connId, cw)
        return this
    }
    
    fun getActiveCp(cpId: Long) = activeCpwMap[cpId]!!.cp
    
    fun getActiveCw(cpId: Long, connId: Long) = activeCpwMap[cpId]!!.activeCwMap[connId]!!
    
    fun getActiveUserCw(cpId: Long): ConnectionWrapper = activeCpwMap[cpId]!!.getActiveUserCw()
    
    private class ConnectionProfileWrapper(val cp: ConnectionProfile) {
        
        val cpId: Long = cp.id
        var hasActiveUserConn = false
        var activeUserConnId = -1L
        
        /**
         * @see ConnectionWrapper.connId
         */
        val activeCwMap = mutableMapOf<Long, ConnectionWrapper>()
        
        fun getActiveUserCw(): ConnectionWrapper {
            if (!hasActiveUserConn) ierror("Active user connection not exist!")
            return activeCwMap[activeUserConnId]!!
        }
    }
}