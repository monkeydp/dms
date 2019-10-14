package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module
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
        init(event)
        unzipAllModules()
    }

    private fun init(event: ApplicationPreparedEvent) {
        val env = event.applicationContext.environment
        initModuleDir(env)
    }

    private fun initModuleDir(env: ConfigurableEnvironment) {
        val moduleDirPath: String = env.getProperty(Module.dirPropertyName)!!
        Module.dir = File(moduleDirPath)
    }

    private fun unzipAllModules() {
        val moduleZips: Array<File> = FileUtil.listFiles(Module.dir,
                FilenameFilter { _, filename ->
                    filename.matches(Module.zipFilenameRegex)
                }
        )
        moduleZips.forEach { file ->
            val zipFile = ZipFile(file)
            zipFile.extractAll(Module.dir.absolutePath)
        }
    }
}