package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module
import com.monkeydp.daios.dms.bundle.DmBundle
import com.monkeydp.daios.dms.sdk.dm.DmMetadata
import com.monkeydp.tools.util.FileUtil
import com.monkeydp.tools.util.YamlUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.io.FilenameFilter

/**
 * @author iPotato
 * @date 2019/10/14
 */
@Component
class ModuleDeployer {

    @Autowired
    private lateinit var moduleRegistry: ModuleRegistry

    fun deployAllModules() {
        val moduleDirs: Array<File> = FileUtil.listFiles(Module.modulesDir,
                FilenameFilter { _, filename ->
                    filename.matches(Module.dmRegex)
                }
        )
        moduleDirs.forEach { moduleDir ->
            if (moduleDir.isDirectory)
                deployModule(moduleDir)
        }
    }

    /**
     * Deploy module like dm-mysql
     */
    private fun deployModule(moduleDir: File) {
        val configFile = File(moduleDir, Module.configFilename)
        val dmMetadata = YamlUtil.loadAs(configFile, DmMetadata::class.java)
        val dmClassname = dmMetadata.dmClassname
        val dmBundle = DmBundle(moduleDir, dmClassname)
        moduleRegistry.registerModule(dmBundle)
    }
}