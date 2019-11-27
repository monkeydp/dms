package com.monkeydp.daios.dms.config.env

import java.io.File

/**
 * @author iPotato
 * @date 2019/11/27
 */
object DmsConfigEnvWrapper : AbstractConfigEnvWrapper() {
    private const val rootDirpath = "root-dir"
    private const val dmParentDirpath = "module.dm-parent-dir"
    private const val modulesDirPath = "module.modules-dir"
    
    val rootDir
        get() = File(getProperty(rootDirpath))
    val dmParentDir
        get() = File(getProperty(dmParentDirpath))
    val modulesDir
        get() = File(getProperty(modulesDirPath))
}