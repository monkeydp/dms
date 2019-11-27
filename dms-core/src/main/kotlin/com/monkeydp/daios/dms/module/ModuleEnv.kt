package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.config.env.DmsConfigEnvWrapper
import com.monkeydp.daios.dms.config.env.DmsConfigEnvWrapper.dmParentDir
import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.util.FileUtil
import com.monkeydp.tools.util.SystemUtil
import java.io.File
import java.io.FileFilter

/**
 * @author iPotato
 * @date 2019/11/27
 */
internal object ModuleEnv {
    private val dmRegex = "^dm-.+".toRegex()
    private val dmZipRegex = "^dm-.+.zip$".toRegex()
    private val ignoreDmDirNames = listOf("dm-base")
    
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
        get() =
            FileUtil.listFiles(dmParentDir,
                    FileFilter { file ->
                        !ignoreDmDirNames.contains(file.name) &&
                        file.isDirectory &&
                        file.name.matches(dmRegex)
                    }
            ).toList()
    
    /**
     * Module zips are not in local project
     */
    val outsideModuleZips: List<File>
        get() {
            val moduleZips = mutableListOf<File>()
            dmDirs.forEach { dmDir ->
                val distributionsDir = File(dmDir, "/build/distributions")
                val files = FileUtil.listFiles(distributionsDir,
                        FileFilter { file ->
                            file.isFile && file.name.matches(dmZipRegex)
                        }
                )
                if (files.isEmpty()) ierror("Cannot find dm zip in $distributionsDir!")
                
                if (files.size > 1) ierror("More than one dm zip was found in $distributionsDir!")
                
                val zip = files.get(0)
                moduleZips.add(zip)
            }
            return moduleZips.toList()
        }
    
    val moduleDirs
        get() =
            FileUtil.listFiles(DmsConfigEnvWrapper.modulesDir,
                    FileFilter { file ->
                        file.isDirectory && file.name.matches(dmRegex)
                    }
            ).toList()
}