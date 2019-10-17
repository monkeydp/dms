package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module.dmParentDir
import com.monkeydp.daios.dms.boot.BootContext.Module.dmRegex
import com.monkeydp.daios.dms.boot.BootContext.Module.dmZipRegex
import com.monkeydp.daios.dms.boot.BootContext.Module.modulesDir
import com.monkeydp.daios.dms.boot.BootContext.rootDir
import com.monkeydp.tools.exception.inner.StdInnerException
import com.monkeydp.tools.util.FileUtil
import com.monkeydp.tools.util.SystemUtil.IS_OS_UNIX
import com.monkeydp.tools.util.SystemUtil.IS_OS_WINDOWS
import com.monkeydp.tools.util.SystemUtil.OS_NAME
import net.lingala.zip4j.ZipFile
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
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
        zipAllModules()
        unzipAllModules2Local()
    }

    private fun zipAllModules() {
        val executor = DefaultExecutor()
        dmDirs().forEach { dmDir ->
            val line = String.format("%s -p %s disZip", gradlewPath(), dmDir)
            val cmdLine = CommandLine.parse(line)
            executor.execute(cmdLine)
        }
    }

    private fun gradlewPath(): String {
        val gradlewFilename =
                when {
                    IS_OS_WINDOWS -> "gradlew.bat"
                    IS_OS_UNIX -> "gradlew"
                    else -> throw StdInnerException(
                            String.format("Unknown system %s", OS_NAME)
                    )
                }

        return StringBuilder()
                .append(rootDir.absolutePath)
                .append("/")
                .append(gradlewFilename)
                .toString()
    }

    private fun unzipAllModules2Local() {
        initModulesDir()
        val moduleZips: List<File> = moduleZips()
        moduleZips.forEach { file ->
            val zipFile = ZipFile(file)
            zipFile.extractAll(modulesDir.absolutePath)
        }
    }

    private fun initModulesDir() {
        if (modulesDir.exists())
            modulesDir.deleteRecursively()
        modulesDir.mkdir()
    }

    private fun moduleZips(): List<File> {
        val moduleZips = mutableListOf<File>()
        val dmDirs = dmDirs()
        dmDirs.forEach { dmDir ->
            val distributionsDir = File(dmDir, "/build/distributions")
            val files = FileUtil.listFiles(distributionsDir,
                    FilenameFilter { _, filename ->
                        filename.matches(dmZipRegex)
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

    private fun dmDirs(): Array<File> {
        return FileUtil.listFiles(dmParentDir,
                FilenameFilter { _, filename ->
                    filename.matches(dmRegex)
                }
        )
    }
}