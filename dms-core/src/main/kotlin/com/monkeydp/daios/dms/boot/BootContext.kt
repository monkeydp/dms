package com.monkeydp.daios.dms.boot

import com.monkeydp.tools.util.StringUtil
import org.springframework.core.env.ConfigurableEnvironment
import java.io.File

/**
 * @author iPotato
 * @date 2019/10/14
 */
final object BootContext {

    lateinit var rootDir: File

    object Module {
        lateinit var dmParentDir: File
        lateinit var modulesDir: File
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
            private const val rootDir = "rootDir"
            private const val dmParentDirpath = "module.dm-parent-dirpath"
            private const val modulesPath = "module.modules-path"
        }

        init {
            BootContext.rootDir = File(getProperty(rootDir))
            Module.dmParentDir = File(getProperty(dmParentDirpath))
            Module.modulesDir = File(getProperty(modulesPath))
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
    }
}