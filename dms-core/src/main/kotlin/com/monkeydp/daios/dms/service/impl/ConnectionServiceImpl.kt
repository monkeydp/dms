package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnectionProfileService
import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.service.contract.ConnectionService
import com.monkeydp.tools.exception.inner.StdInnerException
import com.monkeydp.tools.util.StringUtil
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

    /**
     * ConnectionProfile.id → ConnectionProfile
     */
    private val cpMap: Map<Long, ConnectionProfile> = mutableMapOf()

    private fun getDmBundle(cp: ConnectionProfile) = moduleRegistry.getDmBundle(cp.datasource)

    override fun createConnectionProfile(cp: ConnectionProfile): Long {
        fullCp(cp)
        val savedCp = cpService.save(cp)
        return savedCp.id
    }

    private fun fullCp(cp: ConnectionProfile) {
        val dmBundle = getDmBundle(cp)
        val dbDriverName = dmBundle.getDbDriverName(cp.dbVersionId)
        if (StringUtil.isEmpty(dbDriverName))
            throw StdInnerException("Cannot found db driver name!")
        cp.dbDriverName = dbDriverName!!
    }

    override fun getConnectionWrapper(cp: ConnectionProfile): ConnectionWrapper {
        val dmBundle = getDmBundle(cp)
        dmBundle.setSpecificClassLoader(cp.dbVersionId)
        val connection = dmBundle.impls.connectionFactory.getConnection(cp)
        dmBundle.removeSpecificClassLoader()
        return ConnectionWrapper(connection)
    }
}