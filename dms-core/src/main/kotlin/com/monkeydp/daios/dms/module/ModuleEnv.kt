package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.config.env.DmsConfigEnvWrapper
import com.monkeydp.daios.dms.config.env.DmsConfigEnvWrapper.dmParentDir
import com.monkeydp.tools.enumeration.Symbol.HYPHEN
import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.util.FileUtil
import com.monkeydp.tools.util.SystemUtil
import com.monkeydp.tools.util.VersionUtil
import java.io.File
import java.io.FileFilter

/**
 * @author iPotato
 * @date 2019/11/27
 */
internal object ModuleEnv {
    
    private const val distRelativePath = "/src/main/dist"
    private const val distributionsRelativePath = "/build/distributions"
    
    private val dmZipRegex = "^dm-.+.zip$".toRegex()
    
    val gradlewPath: String
        get() {
            val gradlewFilename =
                    when {
                        SystemUtil.IS_OS_WINDOWS -> "gradlew.bat"
                        SystemUtil.IS_OS_UNIX -> "gradlew"
                        else -> ierror("Unknown system ${SystemUtil.OS_NAME}")
                    }
            
            return StringBuilder()
                    .append(DmsConfigEnvWrapper.rootDir.absolutePath)
                    .append("/")
                    .append(gradlewFilename)
                    .toString()
        }
    
    val dmDirs
        get() = FileUtil.listFiles(dmParentDir, FileFilter { isDmDir(it) }).toList()
    
    /**
     * Dm zips are not in local project
     */
    val dmZips: List<File>
        get() {
            val dmZips = mutableListOf<File>()
            dmDirs.forEach { dmDir ->
                val dir = getDistributionsDir(dmDir)
                val multiVersionZips = FileUtil.listFiles(dir, FileFilter { isDmZip(it) })
                if (multiVersionZips.isEmpty()) ierror("Cannot find dm zip in $dir!")
                dmZips.add(findLasted(multiVersionZips))
            }
            return dmZips.toList()
        }
    
    val moduleDirs
        get() = FileUtil.listFiles(DmsConfigEnvWrapper.modulesDir, FileFilter { isModuleDir(it) }).toList()
    
    private fun getDistributionsDir(dmDir: File) = File(dmDir, distributionsRelativePath)
    
    private fun isDmZip(file: File) = file.isFile && file.name.matches(dmZipRegex)
    
    private fun isDmDir(dir: File) = isModuleDir(dir, distRelativePath)
    
    private fun isModuleDir(dir: File, bootFileRelativePath: String = "") =
            ModuleBootFile(dir, bootFileRelativePath).isValid()
    
    private fun findLasted(multiVersionZips: Array<File>): File {
        var lastedZip: File = multiVersionZips.first()
        fun File.getDmVersion() = name.split(HYPHEN)[2]
        fun File.newerThan(another: File) = VersionUtil.newerThan(getDmVersion(), another.getDmVersion())
        multiVersionZips.forEach { if (lastedZip.newerThan(it)) lastedZip = it }
        return lastedZip
    }
}