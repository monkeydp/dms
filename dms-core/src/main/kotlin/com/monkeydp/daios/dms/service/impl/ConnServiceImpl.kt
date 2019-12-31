package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.component.UserSession
import com.monkeydp.daios.dms.conn.BelongsTo
import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.api.ConnApi
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findImpl
import com.monkeydp.daios.dms.service.contract.ConnManager
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.tools.ext.logger.getLogger
import com.monkeydp.tools.ext.main.ierror
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/18
 */
@Service
internal class ConnServiceImpl @Autowired constructor(
        private val session: UserSession,
        private val registry: ModuleRegistry,
        private val cpService: ConnProfileService,
        private val manager: ConnManager
) : ConnService {
    
    companion object {
        private val log = getLogger()
    }
    
    private val api: ConnApi get() = dmKodeinRepo.findImpl()
    
    override fun saveCp(cp: ConnProfile): ConnProfile {
        val saved = cpService.save(cp.full())
        // TODO should be delegated to listener
        manager.updateActiveCp(saved)
        return saved
    }
    
    private fun ConnProfile.full() = copy(userId = session.userId)
    
    override fun openConn(cpId: Long, belongsTo: BelongsTo) =
            when (belongsTo) {
                BelongsTo.USER -> openUserConn(cpId)
                else -> openOtherConn(cpId, belongsTo)
            }
    
    private fun openUserConn(cpId: Long): ConnWrapper {
        val activeUserCw = manager.getActiveUserCwOrNull(cpId)
        if (activeUserCw != null) {
            log.debug("${activeUserCw.conn} is active ,return it directly.")
            return activeUserCw
        }
        return innerOpenConn(cpId)
    }
    
    private fun openOtherConn(cpId: Long, belongsTo: BelongsTo) = innerOpenConn(cpId, belongsTo)
    
    private fun innerOpenConn(cpId: Long, belongsTo: BelongsTo = BelongsTo.USER): ConnWrapper {
        val cp = findCp(cpId)
        val conn = getConn(cp)
        val cw = ConnWrapper(conn, belongsTo)
        manager.run {
            activateCp(cp)
            activateCw(cw)
        }
        return cw
    }
    
    override fun findCp(cpId: Long) = manager.getActiveCpOrNull(cpId) ?: cpService.findById(cpId)
    
    private fun getConn(cp: ConnProfile): Conn<*> {
        val api: ConnApi = dmKodeinRepo.findImpl(cp.datasource)
        return api.getConn(cp)
    }
    
    override fun closeConn(cpId: Long, connId: Long?): Unit =
            if (connId == null) closeUserConn(cpId)
            else closeOtherConn(cpId, connId)
    
    private fun closeUserConn(cpId: Long): Unit = manager.inactivateUserCw(cpId)
    
    private fun closeOtherConn(cpId: Long, connId: Long): Unit = manager.inactivateCw(cpId, connId)
    
    override fun testConn(cpId: Long) {
        val cp = findCp(cpId)
        testConn(cp)
    }
    
    override fun testConn(cp: ConnProfile): Unit =
            getConn(cp).use { if (!it.isValid()) ierror("Test conn fail, please check conn profile: $cp") }
    
    override fun findActiveCw(cpId: Long, connId: Long?): ConnWrapper =
            if (connId == null) manager.getActiveUserCw(cpId)
            else manager.getActiveCw(cpId, connId)
    
    override fun findActiveCwOrNull(cpId: Long, connId: Long?) =
            if (connId == null) manager.getActiveUserCwOrNull(cpId)
            else manager.getActiveCwOrNull(cpId, connId)
    
    private fun findUserCwOrNull(cpId: Long) = manager.getActiveUserCwOrNull(cpId)
}