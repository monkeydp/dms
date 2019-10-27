package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.connection.ConnectionManager
import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnectionProfileService
import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
import com.monkeydp.daios.dms.service.contract.ConnectionService
import com.monkeydp.tools.ierror
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/18
 */
@Service
internal class ConnectionServiceImpl : ConnectionService {
    
    @Autowired
    private lateinit var moduleRegistry: ModuleRegistry
    @Autowired
    private lateinit var cpService: ConnectionProfileService
    @Autowired
    private lateinit var manager: ConnectionManager
    
    private fun getDmBundle(cp: ConnectionProfile) = moduleRegistry.getDmBundle(cp.datasource)
    
    override fun saveCp(cp: ConnectionProfile) = cpService.save(fullCp(cp))
    
    private fun fullCp(cp: ConnectionProfile): ConnectionProfile {
        val dmBundle = getDmBundle(cp)
        val driverClassname = dmBundle.getDsDriverClassname(cp.dsVersion)
        return cp.copy(dsDriverClassname = driverClassname)
    }
    
    override fun openConn(cpId: Long): ConnectionWrapper {
        val cp = cpService.findById(cpId)
        val cw = getConnectionWrapper(cp)
        manager.activateCp(cp).activateCw(cw)
        return cw
    }
    
    private fun getConnectionWrapper(cp: ConnectionProfile): ConnectionWrapper {
        val dmBundle = getDmBundle(cp)
        dmBundle.setSpecificClassLoader(cp.dsVersion)
        val connection = dmBundle.apis.connFactory.getConnection(cp)
        dmBundle.removeSpecificClassLoader()
        return ConnectionWrapper(connection)
    }
    
    override fun closeConn(cpId: Long) = manager.inactivateUserCw(cpId, true)
    
    override fun testConn(cpId: Long) {
        val cp = cpService.findById(cpId)
        testConn(cp)
    }
    
    override fun testConn(cp: ConnectionProfile) {
        val cw = getConnectionWrapper(cp)
        cw.use { if (!cw.connection.isValid()) ierror("Test connection fail, please check connection profile: $cp") }
    }
}