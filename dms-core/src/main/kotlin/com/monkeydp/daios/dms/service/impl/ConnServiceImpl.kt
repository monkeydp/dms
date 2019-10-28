package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.component.UserSession
import com.monkeydp.daios.dms.conn.ConnManager
import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.tools.ierror
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/18
 */
@Service
internal class ConnServiceImpl : ConnService {
    
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
    
    override fun openConn(cpId: Long): ConnWrapper {
        val activeUserCw = manager.getActiveUserCw(cpId, true)
        if (activeUserCw != null) return activeUserCw
    
        val cp = getCp(cpId)
        val cw = getConnWrapper(cp)
        manager.activateCp(cp).activateCw(cw)
        return cw
    }
    
    private fun getCp(cpId: Long) = manager.getActiveCp(cpId, true) ?: cpService.findById(cpId)
    
    private fun getConnWrapper(cp: ConnProfile): ConnWrapper {
        val dmBundle = registry.getDmBundle(cp)
        dmBundle.setSpecificClassLoader(cp.dsVersion)
        val conn = dmBundle.apis.connApi.getConn(cp)
        dmBundle.removeSpecificClassLoader()
        return ConnWrapper(conn)
    }
    
    override fun closeConn(cpId: Long) = manager.inactivateUserCw(cpId, true)
    
    override fun testConn(cpId: Long) {
        val cp = getCp(cpId)
        testConn(cp)
    }
    
    override fun testConn(cp: ConnProfile) {
        val cw = getConnWrapper(cp)
        cw.use { if (!cw.conn.isValid()) ierror("Test conn fail, please check conn profile: $cp") }
    }
}