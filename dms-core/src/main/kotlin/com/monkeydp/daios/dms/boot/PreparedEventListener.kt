package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module
import com.monkeydp.tools.exception.inner.StdInnerException
import com.monkeydp.tools.util.FileUtil
import net.lingala.zip4j.ZipFile
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import java.io.File
import java.io.FilenameFilter

/**
 * @author iPotato
 * @date 2019/10/8
 */
class PreparedEventListener : ApplicationListener<ApplicationPreparedEvent> {

    private lateinit var env: ConfigurableEnvironment

    override fun onApplicationEvent(event: ApplicationPreparedEvent) {
        env = event.applicationContext.environment
        BootContext.init(env)
        unzipAllModules()
    }

    private fun unzipAllModules() {
        val moduleZips: List<File> = moduleZips()
        moduleZips.forEach { file ->
            val zipFile = ZipFile(file)
            zipFile.extractAll(Module.modulesDir.absolutePath)
        }
    }

    private fun moduleZips(): List<File> {
        val dmDirs = FileUtil.listFiles(Module.dmParentDir,
                FilenameFilter { _, filename ->
                    filename.matches(Module.dmRegex)
                }
        )

        val moduleZips = mutableListOf<File>()
        dmDirs.forEach { dmDir ->
            val distributionsDir = File(dmDir, "/build/distributions")
            val files = FileUtil.listFiles(distributionsDir,
                    FilenameFilter { _, filename ->
                        filename.matches(Module.dmZipRegex)
                    }
            )
            if (files.isEmpty())
                throw StdInnerException(String.format("Cannot find dm zip in %s!", distributionsDir))

            if (files.size > 1)
                throw StdInnerException(String.format("More than one dm zip was found in %s", distributionsDir))

            val zip = files.get(0)
            moduleZips.add(zip)
        }
        return moduleZips.toList()
    }
}