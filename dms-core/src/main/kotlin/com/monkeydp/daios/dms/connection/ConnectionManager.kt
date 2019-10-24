package com.monkeydp.daios.dms.connection

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
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
    
    fun activateConn(cw: ConnectionWrapper): ConnectionManager {
        val cpw = activeCpwMap[cw.cpId]!!
        cpw.activateConn(cw)
        return this
    }
    
    fun getActiveCp(cpId: Long) = activeCpwMap[cpId]!!.cp
    
    fun getActiveCw(cpId: Long, connId: Long) = activeCpwMap[cpId]!!.getActiveCw(connId)
    
    fun getActiveUserCw(cpId: Long): ConnectionWrapper = activeCpwMap[cpId]!!.getActiveUserCw()
    
    private class ConnectionProfileWrapper(val cp: ConnectionProfile) {
        
        val cpId: Long = cp.id
    
        private companion object {
            const val INVALID_CONN_ID = -1L
        }
    
        private var hasActiveUserConn = false
        private var activeUserConnId = INVALID_CONN_ID
            set(value) {
                hasActiveUserConn = value != INVALID_CONN_ID
                field = value
            }
        
        /**
         * @see ConnectionWrapper.connId
         */
        private val activeCwMap = mutableMapOf<Long, ConnectionWrapper>()
    
        @Synchronized
        fun activateConn(cw: ConnectionWrapper) {
            if (cw.belongsToUser()) {
                if (hasActiveUserConn) ierror("Active user connection is already exist!")
                activeUserConnId = cw.connId
            }
            activeCwMap.putIfAbsent(cw.connId, cw)
        }
    
        fun getActiveCw(connId: Long): ConnectionWrapper {
            val activeCw = activeCwMap[connId]
            if (activeCw == null) ierror("The active connection corresponding to id ${connId} does not exist")
            return activeCwMap[connId]!!
        }
        
        fun getActiveUserCw(): ConnectionWrapper {
            if (!hasActiveUserConn) ierror("Active user connection is not exist!")
            return getActiveCw(activeUserConnId)
        }
    }
}