package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.module.ModuleEnv.moduleDirs
import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.daios.dms.sdk.share.kodein.dmsKodeinModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
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
class ModuleRegistrar : CommandLineRunner {
    
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var eventPublisher: EventPublisher
    
    override fun run(vararg args: String?) {
        dmsKodeinModule = Kodein.Module("dmsKodeinModule") {
            bind<EventPublisher>() with singleton { eventPublisher }
        }
        val modules = moduleDirs.map { Module(it) }
        registry.registerAllModule(modules)
    }
}