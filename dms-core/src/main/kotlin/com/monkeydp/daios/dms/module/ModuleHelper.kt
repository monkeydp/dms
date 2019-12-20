package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.config.DmsDirpath
import com.monkeydp.daios.dms.config.kodein
import com.monkeydp.daios.dms.module.ModuleEnv.dmDirs
import com.monkeydp.daios.dms.module.ModuleEnv.dmZips
import com.monkeydp.tools.gradle.wrapper.GradleWrapperExecutor
import com.monkeydp.tools.gradle.wrapper.gradleWrapperExecutor
import net.lingala.zip4j.ZipFile
import org.kodein.di.generic.instance
import java.io.File

object ModuleHelper {
    
    private val executor: GradleWrapperExecutor by kodein.instance()
    
    fun deployAllModules2Local() {
        zipAllModules()
        extractAllModules2Local()
    }
    
    private fun zipAllModules() {
        dmDirs.forEach { dmDir ->
            executor.gradlew {
                +"distZip"
                +Pair("-p", dmDir.absolutePath)
            }
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