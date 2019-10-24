package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module.dmDirs
import com.monkeydp.daios.dms.boot.BootContext.Module.moduleDirs
import com.monkeydp.daios.dms.boot.BootContext.Module.modulesDir
import com.monkeydp.daios.dms.boot.BootContext.Module.outsideModuleZips
import com.monkeydp.daios.dms.boot.BootContext.gradlewPath
import com.monkeydp.tools.ierror
import com.monkeydp.tools.util.FileUtil
import net.lingala.zip4j.ZipFile
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import java.io.File
import java.io.FileFilter

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
        outsideModuleZips = initOutsideModuleZips()
        extractAllModules2Local()
        moduleDirs = initModuleDirs()
    }
    
    private fun zipAllModules() {
        val executor = DefaultExecutor()
        dmDirs.forEach { dmDir ->
            val line = "$gradlewPath -p $dmDir disZip"
            val cmdLine = CommandLine.parse(line)
            executor.execute(cmdLine)
        }
    }
    
    private fun extractAllModules2Local() {
        emptyModulesDir()
        outsideModuleZips.forEach { file ->
            val zipFile = ZipFile(file)
            zipFile.extractAll(modulesDir.absolutePath)
        }
    }
    
    private fun emptyModulesDir() {
        if (modulesDir.exists())
            modulesDir.deleteRecursively()
        modulesDir.mkdir()
    }
    
    private fun initOutsideModuleZips(): List<File> {
        val moduleZips = mutableListOf<File>()
        dmDirs.forEach { dmDir ->
            val distributionsDir = File(dmDir, "/build/distributions")
            val files = FileUtil.listFiles(distributionsDir,
                    FileFilter { file ->
                        file.isFile && file.name.matches(BootContext.Module.dmZipRegex)
                    }
            )
            if (files.isEmpty()) ierror("Cannot find dm zip in $distributionsDir!")
    
            if (files.size > 1) ierror("More than one dm zip was found in $distributionsDir!")
            
            val zip = files.get(0)
            moduleZips.add(zip)
        }
        return moduleZips.toList()
    }
    
    private fun initModuleDirs(): List<File> {
        return FileUtil.listFiles(modulesDir,
                FileFilter { file ->
                    file.isDirectory && file.name.matches(BootContext.Module.dmRegex)
                }
        )
                .toList()
    }
}