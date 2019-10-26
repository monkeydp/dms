package com.monkeydp.daios.dms.connection

import com.monkeydp.daios.dms.curd.service.contract.ConnectionProfileService
import com.monkeydp.daios.dms.sdk.connection.CpMocker.cpMap
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion
import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/24
 */
@Component
class ConnectionProfileInserter : CommandLineRunner {
    @Autowired
    private lateinit var service: ConnectionProfileService

    override fun run(vararg args: String?) {
        val savedMap = mutableMapOf<DsVersion, ConnectionProfile>()
        cpMap.forEach { _, cp ->
            val saved = service.save(cp)
            savedMap.put(saved.dsVersion, saved)
        }
        cpMap.clear()
        cpMap.putAll(savedMap)
    }
}