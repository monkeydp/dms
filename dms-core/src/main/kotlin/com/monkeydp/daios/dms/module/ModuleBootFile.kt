package com.monkeydp.daios.dms.module

import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.util.YamlUtil
import java.io.File

/**
 * @author iPotato
 * @date 2019/11/27
 */
class ModuleBootFile(
        private val dir: File,
        bootFileRelativePath: String = "",
        bootFilename: String = "boot.yml",
        autoCheck: Boolean = false
) {
    private val file = File(dir, "$bootFileRelativePath/$bootFilename")
    
    init {
        if (autoCheck) check()
    }
    
    private fun check() {
        checkModuleDir()
        checkFile()
    }
    
    private fun checkModuleDir() {
        if (!isModuleDirValid()) ierror("Module dir is not valid!")
    }
    
    private fun checkFile() {
        if (!isFileValid()) ierror("Boot file is not valid!")
    }
    
    fun isValid() = isModuleDirValid() && isFileValid()
    
    private fun isModuleDirValid() = dir.exists() && dir.isDirectory
    
    private fun isFileValid() = file.exists() && file.isFile
    
    fun getBootConfig() = YamlUtil.loadAs<ModuleBootConfig>(file)
}