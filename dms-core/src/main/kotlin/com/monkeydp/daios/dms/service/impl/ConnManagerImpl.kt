package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.daios.dms.sdk.helper.IdHelper.INVALID_ID
import com.monkeydp.daios.dms.service.contract.ConnManager
import com.monkeydp.tools.exception.inner.AbstractInnerException
import com.monkeydp.tools.ext.ierror
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/24
 */
@Service
class ConnManagerImpl : ConnManager {
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
    
    override fun activateCp(cp: ConnProfile): ConnManager {
        if (activeCpwMap.contains(cp.id)) return this
        activateCpw(cp)
        return this
    }
    
    private fun activateCpw(cp: ConnProfile) {
        val cpw = ConnProfileWrapper(cp)
        activeCpwMap.putIfAbsent(cpw.cpId, cpw)
    }
    
    override fun updateActiveCp(cp: ConnProfile, ignoreNotFound: Boolean): ConnManager {
        val cpw = getActiveCpw(cp.id, ignoreNotFound) ?: return this
        activeCpwMap[cp.id] = cpw.copy(cp = cp)
        return this
    }
    
    override fun activateCw(cw: ConnWrapper): ConnManager {
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
    
    override fun getActiveCp(cpId: Long) = getActiveCp(cpId, false)!!
    
    override fun getActiveCp(cpId: Long, ignoreNotFound: Boolean) = getActiveCpw(cpId, ignoreNotFound)?.cp
    
    override fun getActiveCw(cpId: Long, connId: Long) = getActiveCw(cpId, connId, false)!!
    
    override fun getActiveCw(cpId: Long, connId: Long, ignoreNotFound: Boolean) =
            getActiveCpw(cpId, ignoreNotFound)?.getActiveCw(connId, ignoreNotFound)
    
    fun getActiveUserCw(cpId: Long) = getActiveUserCw(cpId, false)!!
    
    override fun getActiveUserCw(cpId: Long, ignoreNotFound: Boolean) =
            getActiveCpw(cpId, ignoreNotFound)?.getActiveUserCw(ignoreNotFound)
    
    override fun inactivateCp(cpId: Long, ignoreNotFound: Boolean) {
        inactivateCpw(cpId, ignoreNotFound)
    }
    
    override fun inactivateAllCp() {
        inactivateAllCpw()
    }
    
    private fun inactivateCpw(cpId: Long, ignoreNotFound: Boolean) {
        val activeCpw = getActiveCpw(cpId, ignoreNotFound) ?: return
        activeCpw.inactivateAllCw()
        activeCpwMap.remove(activeCpw.cpId)
    }
    
    private fun inactivateAllCpw() {
        activeCpwMap.forEach { it.value.inactivateAllCw() }
        activeCpwMap.clear()
    }
    
    override fun inactivateCw(cpId: Long, connId: Long, ignoreNotFound: Boolean) {
        val activeCpw = getActiveCpw(cpId, ignoreNotFound) ?: return
        activeCpw.inactivateCw(connId)
    }
    
    override fun inactivateUserCw(cpId: Long, ignoreNotFound: Boolean) {
        val activeCpw = getActiveCpw(cpId, ignoreNotFound) ?: return
        activeCpw.inactivateUserCw(activeCpw.getActiveUserCw(ignoreNotFound))
    }
    
    override fun inactivateAllCw(cpId: Long) {
        val activeCpw = getActiveCpw(cpId, true) ?: return
        activeCpw.inactivateAllCw()
    }
    
    private data class ConnProfileWrapper(
            val cp: ConnProfile,
            /**
             * @see ConnWrapper.connId
             */
            private val activeCwMap: MutableMap<Long, ConnWrapper> = mutableMapOf(),
            private var activeUserConnId: Long = INVALID_ID
    ) {
        val cpId: Long = cp.id
    
        private fun hasActiveUserConn() = IdHelper.isValid(activeUserConnId)
        
        fun activateCw(cw: ConnWrapper) {
            if (cw.belongsToUser()) activateUserCw(cw)
            else activeOtherCw(cw)
        }
        
        @Synchronized
        private fun activateUserCw(cw: ConnWrapper) {
            cw.checkBelongsToUser()
            if (hasActiveUserConn()) ierror("Active user conn is already exist!")
            activeUserConnId = cw.connId
            activeCwMap.putIfAbsent(activeUserConnId, cw)
        }
        
        private fun activeOtherCw(cw: ConnWrapper) {
            cw.checkNotBelongsToUser()
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
            return if (!hasActiveUserConn()) {
                if (!ignoreNotFound) ierror("Active user conn is not exist!")
                null
            } else getActiveCw(activeUserConnId, ignoreNotFound)
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
            cw.checkBelongsToUser()
            innerInactivateCw(cw)
            resetActiveUserConnId()
        }
        
        fun inactivateOtherCw(cw: ConnWrapper) {
            cw.checkNotBelongsToUser()
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
            activeUserConnId = INVALID_ID
        }
        
        class ActiveCwNotFoundException : AbstractInnerException {
            constructor(connId: Long) : super("The active conn wrapper corresponding to connId ${connId} not found")
        }
    }
    
    class ActiveCpwNotFoundException : AbstractInnerException {
        constructor(cpId: Long) : super("The active conn profile wrapper corresponding to cpId ${cpId} not found")
    }
}
