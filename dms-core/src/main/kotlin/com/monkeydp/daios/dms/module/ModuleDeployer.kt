package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.boot.BootContext.Module
import com.monkeydp.daios.dms.boot.BootContext.Module.moduleDirs
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
    
    fun deployAllModules() {
        moduleDirs.forEach { moduleDir ->
            deployModule(moduleDir)
        }
    }
    
    /**
     * Deploy module like dm-mysql
     */
    private fun deployModule(moduleDir: File) {
        val configFile = File(moduleDir, Module.configFilename)
        val config = YamlUtil.loadAs<ModuleConfig>(configFile)
        val dmClassname = config.dmClassname
        val dmBundle = ModuleBundle(moduleDir, dmClassname)
        moduleRegistry.registerModule(dmBundle)
    }
}