package com.monkeydp.daios.dms.boot

import org.springframework.core.env.ConfigurableEnvironment
import java.io.File

/**
 * @author iPotato
 * @date 2019/10/14
 */
final object BootContext {

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

    private final class ContextInitializer(env: ConfigurableEnvironment) {

        private val dmPathPropertyName = "dms.module.dm-parent-dirpath"
        private val modulesPathPropertyName = "dms.module.modules-path"

        init {
            Module.dmParentDir = File(env.getProperty(dmPathPropertyName)!!)
            Module.modulesDir = File(env.getProperty(modulesPathPropertyName)!!)
        }
    }
}