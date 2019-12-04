package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.module.ModuleEnv.moduleDirs
import com.monkeydp.daios.dms.sdk.dm.DmConfig
import com.monkeydp.daios.dms.sdk.event.EventPublisher
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/14
 */
@Component
class ModuleDeployer {
    
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var eventPublisher: EventPublisher
    
    fun deployAllModules() {
        moduleDirs.forEach {
            val config = DmConfig(
                    deployDir = it,
                    kotlinModule = Kodein.Module("openKodeinModule") {
                        bind<EventPublisher>() with singleton { eventPublisher }
                    }
            )
            val module = Module(config)
            registry.registerModule(module)
        }
    }
}