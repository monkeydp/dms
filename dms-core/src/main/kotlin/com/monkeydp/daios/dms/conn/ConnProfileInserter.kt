package com.monkeydp.daios.dms.conn

import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.dm.DmTestdataRegistry
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/24
 */
@Component
class ConnProfileInserter : CommandLineRunner {
    @Autowired
    private lateinit var service: ConnProfileService
    
    override fun run(vararg args: String?) {
        val saveds = mutableListOf<ConnProfile>()
        val cps = DmTestdataRegistry.cps
        cps.forEach { cp ->
            val saved = service.save(cp)
            saveds.add(saved)
        }
        cps.clear()
        cps.addAll(saveds)
    }
}