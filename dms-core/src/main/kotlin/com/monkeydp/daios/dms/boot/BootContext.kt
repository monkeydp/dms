package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.boot.BootContext.Module.dmDirs
import com.monkeydp.daios.dms.boot.BootContext.Module.dmParentDir
import com.monkeydp.daios.dms.boot.BootContext.Module.modulesDir
import com.monkeydp.tools.exception.inner.StdInnerException
import com.monkeydp.tools.util.FileUtil
import com.monkeydp.tools.util.StringUtil
import com.monkeydp.tools.util.SystemUtil
import org.springframework.core.env.ConfigurableEnvironment
import java.io.File
import java.io.FileFilter

/**
 * @author iPotato
 * @date 2019/10/14
 */
final object BootContext {

    lateinit var rootDir: File
    lateinit var gradlewPath: String

    object Module {
        lateinit var dmParentDir: File
        lateinit var modulesDir: File

        lateinit var dmDirs: List<File>
        /**
         * Module zips are not in local project
         */
        lateinit var outsideModuleZips: List<File>
        lateinit var moduleDirs: List<File>

        const val configFilename = "module.yml"
        val dmRegex = "^dm-.+".toRegex()
        val dmZipRegex = "^dm-.+.zip$".toRegex()
    }

    fun init(env: ConfigurableEnvironment) {
        ContextInitializer(env)
    }

    private final class ContextInitializer(private val env: ConfigurableEnvironment) {

        private val prefix = "dms"

        companion object PropertyName {
            private const val rootDirpath = "root-dir"
            private const val dmParentDirpath = "module.dm-parent-dir"
            private const val modulesDirPath = "module.modules-dir"
        }

        init {
            rootDir = File(getProperty(rootDirpath))
            dmParentDir = File(getProperty(dmParentDirpath))
            modulesDir = File(getProperty(modulesDirPath))

            gradlewPath = initGradlewPath()
            dmDirs = initDmDirs()
        }

        private fun getProperty(propertyName: String): String {
            return env.getProperty(
                    fullPropertyName(propertyName)
            )!!
        }

        private fun fullPropertyName(propertyName: String): String {
            val builder = StringBuilder()
            if (!StringUtil.isEmpty(prefix))
                builder.append(prefix)
                        .append(".")
            builder.append(propertyName)
            return builder.toString()
        }

        private fun initGradlewPath(): String {
            val gradlewFilename =
                    when {
                        SystemUtil.IS_OS_WINDOWS -> "gradlew.bat"
                        SystemUtil.IS_OS_UNIX -> "gradlew"
                        else -> throw StdInnerException(
                                String.format("Unknown system %s", SystemUtil.OS_NAME)
                        )
                    }

            return StringBuilder()
                    .append(rootDir.absolutePath)
                    .append("/")
                    .append(gradlewFilename)
                    .toString()
        }

        private fun initDmDirs(): List<File> {
            return FileUtil.listFiles(dmParentDir,
                    FileFilter { file ->
                        file.isDirectory && file.name.matches(Module.dmRegex)
                    }
            ).toList()
        }
    }
}