package com.monkeydp.daios.dms.conn

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.util.IdUtil
import com.monkeydp.tools.exception.inner.AbstractInnerException
import com.monkeydp.tools.ierror
import org.springframework.stereotype.Component

/**
 * TODO consider concurrency
 * @author iPotato
 * @date 2019/10/24
 */
@Component
class ConnManager {
    /**
     * @see ConnProfile.id
     */
    private val activeCpwMap = mutableMapOf<Long, ConnProfileWrapper>()
    
    /**
     * Regularly clean up expired conn
     */
    private fun autoClean() {
        // TODO
    }
    
    fun activateCp(cp: ConnProfile): ConnManager {
        val cpw = ConnProfileWrapper(cp)
        activeCpwMap.putIfAbsent(cpw.cpId, cpw)
        return this
    }
    
    fun activateCw(cw: ConnWrapper): ConnManager {
        val cpw = getActiveCpw(cw.cpId)
        cpw.activateCw(cw)
        return this
    }
    
    private fun getActiveCpw(cpId: Long) = getActiveCpw(cpId, false)!!
    
    private fun getActiveCpw(cpId: Long, ignoreNotFound: Boolean): ConnProfileWrapper? {
        val cpw = activeCpwMap[cpId]
        if (cpw == null && !ignoreNotFound) throw ActiveCpwNotFoundException(cpId)
        return cpw
    }
    
    fun getActiveCp(cpId: Long) = getActiveCp(cpId, false)!!
    
    fun getActiveCp(cpId: Long, ignoreNotFound: Boolean) = getActiveCpw(cpId, ignoreNotFound)?.cp
    
    fun getActiveCw(cpId: Long, connId: Long) = getActiveCw(cpId, connId, false)!!
    
    fun getActiveCw(cpId: Long, connId: Long, ignoreNotFound: Boolean) = getActiveCpw(cpId, ignoreNotFound)?.getActiveCw(connId, ignoreNotFound)
    
    fun getActiveUserCw(cpId: Long) = getActiveUserCw(cpId, false)!!
    
    fun getActiveUserCw(cpId: Long, ignoreNotFound: Boolean) = getActiveCpw(cpId, ignoreNotFound)?.getActiveUserCw(ignoreNotFound)
    
    fun inactivateCp(cpId: Long) {
        inactivateCpw(cpId)
    }
    
    fun inactivateAllCp() {
        inactivateAllCpw()
    }
    
    private fun inactivateCpw(cpId: Long) {
        val activeCpw = getActiveCpw(cpId, true) ?: return
        activeCpw.inactivateAllCw()
        activeCpwMap.remove(activeCpw.cpId)
    }
    
    private fun inactivateAllCpw() {
        activeCpwMap.forEach { it.value.inactivateAllCw() }
        activeCpwMap.clear()
    }
    
    fun inactivateCw(cpId: Long, connId: Long) {
        val activeCpw = getActiveCpw(cpId, true) ?: return
        activeCpw.inactivateCw(connId)
    }
    
    fun inactivateUserCw(cpId: Long, ignoreNotFound: Boolean) {
        val activeCpw = getActiveCpw(cpId, ignoreNotFound) ?: return
        activeCpw.inactivateUserCw(activeCpw.getActiveUserCw(ignoreNotFound))
    }
    
    fun inactivateAllCw(cpId: Long) {
        val activeCpw = getActiveCpw(cpId, true) ?: return
        activeCpw.inactivateAllCw()
    }
    
    private class ConnProfileWrapper(val cp: ConnProfile) {
        
        val cpId: Long = cp.id
    
        private companion object {
            const val INVALID_CONN_ID = IdUtil.INVALID_ID
        }
    
        private var activeUserConnId: Long = INVALID_CONN_ID
    
        fun hasActiveUserConn() = IdUtil.isValid(activeUserConnId)
        
        /**
         * @see ConnWrapper.connId
         */
        private val activeCwMap = mutableMapOf<Long, ConnWrapper>()
        
        private fun checkBelongsToUser(cw: ConnWrapper) {
            if (!cw.belongsToUser()) ierror("Conn must belongs to user!")
        }
        
        private fun checkNotBelongsToUser(cw: ConnWrapper) {
            if (cw.belongsToUser()) ierror("Conn must not belongs to user!")
        }
        
        fun activateCw(cw: ConnWrapper) {
            if (cw.belongsToUser()) activateUserCw(cw)
            else activeOtherCw(cw)
        }
    
        @Synchronized
        private fun activateUserCw(cw: ConnWrapper) {
            checkBelongsToUser(cw)
            if (hasActiveUserConn()) ierror("Active user conn is already exist!")
            activeUserConnId = cw.connId
            activeCwMap.putIfAbsent(cw.connId, cw)
        }
        
        private fun activeOtherCw(cw: ConnWrapper) {
            checkNotBelongsToUser(cw)
            activeCwMap.putIfAbsent(cw.connId, cw)
        }
    
        fun getActiveCw(connId: Long) = getActiveCw(connId, false)!!
        
        fun getActiveCw(connId: Long, ignoreNotFound: Boolean): ConnWrapper? {
            val cw = activeCwMap[connId]
            if (cw == null && !ignoreNotFound) throw ActiveCwNotFoundException(connId)
            return cw
        }
    
        fun getActiveUserCw() = getActiveUserCw(false)!!
        
        fun getActiveUserCw(ignoreNotFound: Boolean): ConnWrapper? {
            if (!hasActiveUserConn() && !ignoreNotFound) ierror("Active user conn is not exist!")
            return getActiveCw(activeUserConnId)
        }
    
        fun inactivateCw(connId: Long) {
            var activeCw = getActiveCw(connId, true) ?: return
            inactivateCw(activeCw)
        }
        
        fun inactivateCw(cw: ConnWrapper) {
            if (cw.belongsToUser()) inactivateUserCw(cw)
            else inactivateOtherCw(cw)
        }
        
        fun inactivateUserCw(cw: ConnWrapper? = getActiveUserCw()) {
            if (cw == null) return
            checkBelongsToUser(cw)
            innerInactivateCw(cw)
            resetActiveUserConnId()
        }
        
        fun inactivateOtherCw(cw: ConnWrapper) {
            checkNotBelongsToUser(cw)
            innerInactivateCw(cw)
        }
        
        private fun innerInactivateCw(cw: ConnWrapper) {
            cw.conn.close()
            activeCwMap.remove(cw.connId)
        }
        
        fun inactivateAllCw() {
            activeCwMap.forEach { it.value.conn.close() }
            activeCwMap.clear()
            resetActiveUserConnId()
        }
    
        private fun resetActiveUserConnId() {
            activeUserConnId = INVALID_CONN_ID
        }
    
        class ActiveCwNotFoundException : AbstractInnerException {
            constructor(connId: Long) : super("The active conn wrapper corresponding to connId ${connId} not found")
        }
    }
    
    class ActiveCpwNotFoundException : AbstractInnerException {
        constructor(cpId: Long) : super("The active conn profile wrapper corresponding to cpId ${cpId} not found")
    }
}