package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.service.contract.ConnectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/18
 */
@Service
internal class ConnectionServiceImpl : ConnectionService {

    @Autowired
    lateinit var moduleRegistry: ModuleRegistry

    /**
     * ConnectionProfile.id → ConnectionProfile
     */
    private val cpMap: Map<Long, ConnectionProfile> = mutableMapOf()

    override fun createConnectionProfile(profile: ConnectionProfile): Long {
        // TODO
        return 10L
    }

    override fun getConnectionWrapper(profile: ConnectionProfile): ConnectionWrapper {
        val dmBundle = moduleRegistry.getDmBundle(profile.datasource)
        dmBundle.setSpecificClassLoader(profile.dbVersionId)
        val connection = dmBundle.impls.connectionFactory.getConnection(profile)
        dmBundle.removeSpecificClassLoader()
        return ConnectionWrapper(connection)
    }
}