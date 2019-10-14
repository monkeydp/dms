package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module
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
        val modules: Array<File> = FileUtil.listFiles(Module.dir,
                FilenameFilter { _, filename ->
                    filename.matches(Module.filenameRegex)
                }
        )
        modules.forEach { module ->
            if (module.isDirectory)
                deployModule(module)
        }
    }

    /**
     * Deploy module like dm-mysql
     */
    private fun deployModule(module: File) {
        val configFile = File(module, Module.configFilename)
        val dmMetadata = YamlUtil.loadAs(configFile, DmMetadata::class.java)
        val dmClassname = dmMetadata.dmClassname

    }
}