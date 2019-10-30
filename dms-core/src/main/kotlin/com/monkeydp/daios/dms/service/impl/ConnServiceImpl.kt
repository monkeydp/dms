package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.component.UserSession
import com.monkeydp.daios.dms.conn.BelongsTo
import com.monkeydp.daios.dms.conn.ConnManager
import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.tools.ext.getLogger
import com.monkeydp.tools.ext.ierror
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/18
 */
@Service
internal class ConnServiceImpl : ConnService {
    
    companion object {
        private val log = getLogger()
    }
    
    @Autowired
    private lateinit var session: UserSession
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var cpService: ConnProfileService
    @Autowired
    private lateinit var manager: ConnManager
    
    override fun saveCp(cp: ConnProfile): ConnProfile {
        val saved = cpService.save(fullCp(cp))
        // TODO should be delegated to listener
        manager.updateActiveCp(saved)
        return saved
    }
    
    private fun fullCp(cp: ConnProfile): ConnProfile {
        val dmBundle = registry.getDmBundle(cp)
        val driverClassname = dmBundle.getDriverClassname(cp.dsVersion)
        return cp.copy(
                userId = session.userId,
                dsDriverClassname = driverClassname
        )
    }
    
    override fun openConn(cpId: Long, belongsTo: BelongsTo): ConnWrapper {
        return when (belongsTo) {
            BelongsTo.USER -> openUserConn(cpId)
            else           -> openOtherConn(cpId, belongsTo)
        }
    }
    
    private fun openUserConn(cpId: Long): ConnWrapper {
        val activeUserCw = manager.getActiveUserCw(cpId, true)
        if (activeUserCw != null) {
            log.debug("Conn(id = ${activeUserCw.connId}) with cpId = $cpId is active ,return it directly.")
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
        val dmBundle = registry.getDmBundle(cp)
        dmBundle.setSpecificClassLoader(cp.dsVersion)
        val conn = dmBundle.apis.connApi.getConn(cp)
        dmBundle.removeSpecificClassLoader()
        return conn
    }
    
    override fun closeConn(cpId: Long, connId: Long?) {
        if (connId == null) closeUserConn(cpId)
        else closeOtherConn(cpId, connId)
    }
    
    private fun closeUserConn(cpId: Long) {
        manager.inactivateUserCw(cpId, true)
    }
    
    private fun closeOtherConn(cpId: Long, connId: Long) {
        manager.inactivateCw(cpId, connId)
    }
    
    override fun testConn(cpId: Long) {
        val cp = findCp(cpId)
        testConn(cp)
    }
    
    override fun testConn(cp: ConnProfile) {
        val conn = getConn(cp)
        conn.use { if (!conn.isValid()) ierror("Test conn fail, please check conn profile: $cp") }
    }
    
    override fun findCw(cpId: Long, connId: Long?): ConnWrapper {
        return if (connId == null) findUserCw(cpId)
        else finOtherCw(cpId, connId)
    }
    
    private fun findUserCw(cpId: Long) = manager.getActiveUserCw(cpId)
    
    private fun finOtherCw(cpId: Long, connId: Long) = manager.getActiveCw(cpId, connId)
}