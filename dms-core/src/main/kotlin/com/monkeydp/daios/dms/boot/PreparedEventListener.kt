package com.monkeydp.daios.dms.boot

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

    override fun onApplicationEvent(event: ApplicationPreparedEvent) {
        val env = event.applicationContext.environment
        unzipAllModules(env)
    }

    private fun unzipAllModules(env: ConfigurableEnvironment) {
        val moduleDirPath: String = env.getProperty("dms.module.dir-path")!!
        val moduleZipRegex = "^dm-.+.zip$".toRegex()
        val modulePaths: Array<File> = FileUtil.listFiles(moduleDirPath,
                FilenameFilter { _, filename ->
                    filename.matches(moduleZipRegex)
                }
        )
        modulePaths.forEach { file ->
            val zipFile = ZipFile(file)
            zipFile.extractAll(moduleDirPath)
        }
    }
}