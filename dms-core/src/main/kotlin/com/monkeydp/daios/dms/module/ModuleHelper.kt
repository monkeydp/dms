package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.config.DmsDirpath
import com.monkeydp.daios.dms.module.ModuleEnv.dmDirs
import com.monkeydp.daios.dms.module.ModuleEnv.dmZips
import com.monkeydp.daios.dms.module.ModuleEnv.gradlewPath
import net.lingala.zip4j.ZipFile
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import java.io.File

object ModuleHelper {
    
    fun deployAllModules2Local() {
        zipAllModules()
        extractAllModules2Local()
    }
    
    private fun zipAllModules() {
        val executor = DefaultExecutor()
        dmDirs.forEach { dmDir ->
            val line = "${gradlewPath} -p $dmDir distZip"
            val cmdLine = CommandLine.parse(line)
            executor.execute(cmdLine)
        }
    }
    
    private fun extractAllModules2Local() {
        emptyModulesDir()
        dmZips.forEach { file ->
            val zipFile = ZipFile(file)
            zipFile.extractAll(DmsDirpath.modules)
        }
    }
    
    private fun emptyModulesDir() {
        val modulesDir = File(DmsDirpath.modules)
        if (modulesDir.exists())
            modulesDir.deleteRecursively()
        modulesDir.mkdir()
    }
}