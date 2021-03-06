package com.monkeydp.daios.dms.conn

import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.module.ModuleTestdata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/24
 */
@Component
@Order(2)
class ConnProfileInserter : CommandLineRunner {
    @Autowired
    private lateinit var service: ConnProfileService
    
    override fun run(vararg args: String?) {
        ModuleTestdata.savedCps = service.saveAll(ModuleTestdata.cps)
    }
}