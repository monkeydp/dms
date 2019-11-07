package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.boot.BootContext.Module
import com.monkeydp.daios.dms.boot.BootContext.Module.moduleDirs
import com.monkeydp.daios.dms.sdk.dm.DmOpenConfig
import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.tools.util.YamlUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

/**
 * @author iPotato
 * @date 2019/10/14
 */
@Component
class ModuleDeployer {
    
    @Autowired
    private lateinit var moduleRegistry: ModuleRegistry
    @Autowired
    private lateinit var eventPublisher: EventPublisher
    
    fun deployAllModules() {
        moduleDirs.forEach { moduleDir ->
            deployModule(moduleDir)
        }
    }
    
    /**
     * Deploy module like dm-mysql
     */
    private fun deployModule(moduleDir: File) {
        val bootFile = File(moduleDir, Module.bootFilename)
        val config = YamlUtil.loadAs<DmBootConfig>(bootFile)
        val classname = config.classname
        val openConfig = DmOpenConfig(moduleDir, eventPublisher)
        val bundle = ModuleBundle(openConfig, classname)
        moduleRegistry.registerModule(bundle)
    }
}