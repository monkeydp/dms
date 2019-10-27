package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
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
    private lateinit var moduleRegistry: ModuleRegistry
    @Autowired
    private lateinit var cpService: ConnProfileService
    @Autowired
    private lateinit var manager: ConnManager
    
    private fun getDmBundle(cp: ConnProfile) = moduleRegistry.getDmBundle(cp.datasource)
    
    override fun saveCp(cp: ConnProfile) = cpService.save(fullCp(cp))
    
    private fun fullCp(cp: ConnProfile): ConnProfile {
        val dmBundle = getDmBundle(cp)
        val driverClassname = dmBundle.getDsDriverClassname(cp.getDsVersion())
        return cp.copy(dsDriverClassname = driverClassname)
    }
    
    override fun openConn(cpId: Long): ConnWrapper {
        val cp = cpService.findById(cpId)
        val cw = getConnWrapper(cp)
        manager.activateCp(cp).activateCw(cw)
        return cw
    }
    
    private fun getConnWrapper(cp: ConnProfile): ConnWrapper {
        val dmBundle = getDmBundle(cp)
        dmBundle.setSpecificClassLoader(cp.getDsVersion())
        val conn = dmBundle.apis.connFactory.getConn(cp)
        dmBundle.removeSpecificClassLoader()
        return ConnWrapper(conn)
    }
    
    override fun closeConn(cpId: Long) = manager.inactivateUserCw(cpId, true)
    
    override fun testConn(cpId: Long) {
        val cp = cpService.findById(cpId)
        testConn(cp)
    }
    
    override fun testConn(cp: ConnProfile) {
        val cw = getConnWrapper(cp)
        cw.use { if (!cw.conn.isValid()) ierror("Test conn fail, please check conn profile: $cp") }
    }
}