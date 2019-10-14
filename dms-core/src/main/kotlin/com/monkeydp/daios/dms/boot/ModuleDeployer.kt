package com.monkeydp.daios.dms.boot

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

    val configFilename = "module.yml"

    fun deployAllModules() {
        val moduleRegex = "^dm-.+".toRegex()
        val modules: Array<File> = FileUtil.listFiles(PreparedEventListener.moduleDir,
                FilenameFilter { _, filename ->
                    filename.matches(moduleRegex)
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
        val configFile = File(module, configFilename)
        val dmMetadata = YamlUtil.loadAs(configFile, DmMetadata::class.java)
        val dmClassname = dmMetadata.dmClassname

    }
}