package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.tools.ext.toStdPath

/**
 * @author iPotato
 * @date 2019/10/30
 */
class DmNewPath(
        deployedDirpath: String,
        classesDirpath: String,
        val classLoader: ClassLoader
) {
    val deployedDirpath: String
    val classesDirpath: String
    
    init {
        this.deployedDirpath = deployedDirpath.toStdPath()
        this.classesDirpath = classesDirpath.toStdPath()
    }
}