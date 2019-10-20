package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.service.contract.DispatcherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/18
 */
@Service
class DispatcherServiceImpl : DispatcherService {
    @Autowired
    lateinit var moduleRegistry: ModuleRegistry

    override fun getConnection(profile: ConnectionProfile): ConnectionWrapper {
        val dmBundle = moduleRegistry.getDmBundle(profile.datasource)
        val connection = dmBundle.impls.connectionFactory.getConnection(profile)
        return ConnectionWrapper(connection)
    }
}