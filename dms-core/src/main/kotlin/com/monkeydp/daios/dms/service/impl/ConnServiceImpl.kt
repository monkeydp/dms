package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.conn.BelongsTo
import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.api.ConnApi
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.dm.DmHelper
import com.monkeydp.daios.dms.service.contract.ConnManager
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.session.UserSession
import com.monkeydp.tools.ext.main.ierror
import com.monkeydp.tools.ext.logger.getLogger
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
    
    private val api: ConnApi get() = DmHelper.findImpl()
    
    override fun saveCp(cp: ConnProfile): ConnProfile {
        val saved = cpService.save(cp.full())
        // TODO should be delegated to listener
        manager.updateActiveCp(saved, true)
        return saved
    }
    
    private fun ConnProfile.full() = copy(userId = session.userId)
    
    override fun openConn(cpId: Long, belongsTo: BelongsTo) =
            when (belongsTo) {
                BelongsTo.USER -> openUserConn(cpId)
                else -> openOtherConn(cpId, belongsTo)
            }
    
    private fun openUserConn(cpId: Long): ConnWrapper {
        val activeUserCw = manager.getActiveUserCw(cpId, true)
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
        manager.activateCp(cp).activateCw(cw)
        return cw
    }
    
    override fun findCp(cpId: Long) = manager.getActiveCp(cpId, true) ?: cpService.findById(cpId)
    
    private fun getConn(cp: ConnProfile): Conn<*> {
        val module = registry.findModule(cp)
        module.setSpecificClassLoader(cp.dsVersion)
        val api = DmHelper.findImpl<ConnApi>(cp.datasource)
        val conn = api.getConn(cp)
        module.removeSpecificClassLoader()
        return conn
    }
    
    override fun closeConn(cpId: Long, connId: Long?): Unit =
            if (connId == null) closeUserConn(cpId)
            else closeOtherConn(cpId, connId)
    
    private fun closeUserConn(cpId: Long): Unit = manager.inactivateUserCw(cpId, true)
    
    private fun closeOtherConn(cpId: Long, connId: Long): Unit = manager.inactivateCw(cpId, connId, true)
    
    override fun testConn(cpId: Long) {
        val cp = findCp(cpId)
        testConn(cp)
    }
    
    override fun testConn(cp: ConnProfile): Unit =
            getConn(cp).use { if (!it.isValid()) ierror("Test conn fail, please check conn profile: $cp") }
    
    override fun findCw(cpId: Long, connId: Long?, ignoreNotFound: Boolean) =
            if (connId == null) findUserCw(cpId, ignoreNotFound)
            else finOtherCw(cpId, connId)
    
    private fun findUserCw(cpId: Long, ignoreNotFound: Boolean) = manager.getActiveUserCw(cpId, ignoreNotFound)
    
    private fun finOtherCw(cpId: Long, connId: Long) = manager.getActiveCw(cpId, connId, true)
}