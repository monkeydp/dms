package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.config.DmsKodeinModule
import com.monkeydp.daios.dms.module.ModuleEnv.moduleDirs
import com.monkeydp.daios.dms.sdk.config.KodeinModuleRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/14
 */
@Component
@Order(1)
class ModuleRegistrar @Autowired constructor(
        private val registry: ModuleRegistry,
        private val dmsKodeinModule: DmsKodeinModule
) : CommandLineRunner {
    
    override fun run(vararg args: String?) {
        KodeinModuleRepo.setDmsKodeinModule(dmsKodeinModule.get())
        val modules = moduleDirs.map { Module(it) }
        registry.registerAllModule(modules)
    }
}