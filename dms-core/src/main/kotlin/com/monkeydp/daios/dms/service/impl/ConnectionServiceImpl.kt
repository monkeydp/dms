package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.curd.service.contract.ConnectionProfileService
import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
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
     * @key ConnectionWrapper.id
     */
    private val cwMap = mutableMapOf<Long, ConnectionWrapper>()

    private fun getDmBundle(cp: ConnectionProfile) = moduleRegistry.getDmBundle(cp.datasource)

    override fun saveConnectionProfile(cp: ConnectionProfile) = cpService.save(fullCp(cp))

    private fun fullCp(cp: ConnectionProfile): ConnectionProfile {
        val dmBundle = getDmBundle(cp)
        val dbDriverName = dmBundle.getDbDriverName(cp.dbVersionId)
        if (StringUtil.isEmpty(dbDriverName))
            throw StdInnerException("Cannot found db driver name!")
        return cp.copy(dbDriverName = dbDriverName!!)
    }

    override fun openConnection(cpId: Long): ConnectionWrapper {
        val cp = cpService.findById(cpId)
        val cw = getConnectionWrapper(cp)
        cwMap[cw.id] = cw
        return cw
    }

    private fun getConnectionWrapper(cp: ConnectionProfile): ConnectionWrapper {
        val dmBundle = getDmBundle(cp)
        dmBundle.setSpecificClassLoader(cp.dbVersionId)
        val connection = dmBundle.impls.connectionFactory.getConnection(cp)
        dmBundle.removeSpecificClassLoader()
        return ConnectionWrapper(connection)
    }
}