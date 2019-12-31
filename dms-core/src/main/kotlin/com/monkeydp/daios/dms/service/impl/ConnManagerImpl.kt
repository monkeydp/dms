package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.daios.dms.sdk.helper.IdHelper.INVALID_ID
import com.monkeydp.daios.dms.service.contract.ConnManager
import com.monkeydp.tools.exception.inner.InnerException
import com.monkeydp.tools.ext.main.ierror
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
    
    override fun activateCp(cp: ConnProfile) {
        if (activeCpwMap.contains(cp.id)) return
        activateCpw(cp)
    }
    
    private fun activateCpw(cp: ConnProfile) {
        val cpw = ConnProfileWrapper(cp)
        activeCpwMap.putIfAbsent(cpw.cpId, cpw)
    }
    
    override fun updateActiveCp(cp: ConnProfile) {
        (getActiveCpwOrNull(cp.id) ?: return).run {
            activeCpwMap[cp.id] = copy(cp = cp)
        }
    }
    
    override fun activateCw(cw: ConnWrapper): ConnManager {
        val cpw = getActiveCpw(cw.cpId)
        cpw.activateCw(cw)
        return this
    }
    
    private fun getActiveCpw(cpId: Long): ConnProfileWrapper =
            activeCpwMap[cpId] ?: throw ActiveCpwNotFoundException(cpId)
    
    private fun getActiveCpwOrNull(cpId: Long): ConnProfileWrapper? =
            activeCpwMap[cpId]
    
    override fun getActiveCp(cpId: Long) = getActiveCpw(cpId).cp
    
    override fun getActiveCpOrNull(cpId: Long) = getActiveCpwOrNull(cpId)?.cp
    
    override fun getActiveCw(cpId: Long, connId: Long) = getActiveCwOrNull(cpId, connId)!!
    
    override fun getActiveCwOrNull(cpId: Long, connId: Long) =
            getActiveCpwOrNull(cpId)?.getActiveCw(connId)
    
    override fun getActiveUserCw(cpId: Long) =
            getActiveCpw(cpId).getActiveUserCw()
    
    override fun getActiveUserCwOrNull(cpId: Long) =
            getActiveCpwOrNull(cpId)?.getActiveUserCwOrNull()
    
    override fun inactivateCp(cpId: Long) {
        inactivateCpw(cpId)
    }
    
    override fun inactivateAllCp() {
        inactivateAllCpw()
    }
    
    private fun inactivateCpw(cpId: Long) {
        (getActiveCpwOrNull(cpId) ?: return).run {
            inactivateAllCw()
            activeCpwMap.remove(cpId)
        }
    }
    
    private fun inactivateAllCpw() {
        activeCpwMap.forEach { it.value.inactivateAllCw() }
        activeCpwMap.clear()
    }
    
    override fun inactivateCw(cpId: Long, connId: Long) {
        (getActiveCpwOrNull(cpId) ?: return).inactivateCw(connId)
    }
    
    override fun inactivateUserCw(cpId: Long) {
        (getActiveCpwOrNull(cpId) ?: return).run {
            inactivateUserCw(getActiveUserCwOrNull())
        }
    }
    
    override fun inactivateAllCw(cpId: Long) {
        (getActiveCpwOrNull(cpId) ?: return).inactivateAllCw()
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
        
        fun getActiveCw(connId: Long) =
                activeCwMap[connId] ?: throw ActiveCwNotFoundException(connId)
        
        fun getActiveCwOrNull(connId: Long): ConnWrapper? =
                activeCwMap[connId]
        
        
        fun getActiveUserCw(): ConnWrapper =
                activeCwMap[activeUserConnId] ?: throw ActiveConnNotFound(cpId, activeUserConnId)
        
        fun getActiveUserCwOrNull(): ConnWrapper? =
                activeCwMap[activeUserConnId]
        
        fun inactivateCw(connId: Long) {
            (getActiveCwOrNull(connId) ?: return).apply(::inactivateCw)
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
        
        class ActiveCwNotFoundException : InnerException {
            constructor(connId: Long) : super("The active ConnWrapper(connId = ${connId}) not found")
        }
        
        class ActiveConnNotFound : InnerException {
            constructor(cpId: Long, connId: Long) : super("Active Conn(cpId = $cpId, id = $connId) not found!")
        }
    }
    
    class ActiveCpwNotFoundException : InnerException {
        constructor(cpId: Long) : super("The active conn profile wrapper corresponding to cpId ${cpId} not found")
    }
}
