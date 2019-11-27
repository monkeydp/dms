package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.module.ModuleEnv.moduleDirs
import com.monkeydp.daios.dms.sdk.event.EventPublisher
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
    private lateinit var publisher: EventPublisher
    
    fun deployAllModules() {
        moduleDirs.forEach { registry.registerModule(Module(it, publisher)) }
    }
}