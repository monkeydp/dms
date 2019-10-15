package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module
import com.monkeydp.daios.dms.bundle.DmBundle
import com.monkeydp.daios.dms.sdk.dm.DmMetadata
import com.monkeydp.tools.util.FileUtil
import com.monkeydp.tools.util.YamlUtil
import org.springframework.stereotype.Component
import java.io.File
import java.io.FilenameFilter

/**
 * @author iPotato
 * @date 2019/10/14
 */
@Component
class ModuleDeployer {

    fun deployAllModules() {
        val moduleDirs: Array<File> = FileUtil.listFiles(Module.parentDir,
                FilenameFilter { _, filename ->
                    filename.matches(Module.filenameRegex)
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
    }
}