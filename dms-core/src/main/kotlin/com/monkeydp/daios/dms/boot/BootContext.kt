package com.monkeydp.daios.dms.boot

import java.io.File

/**
 * @author iPotato
 * @date 2019/10/14
 */
final object BootContext {
    object Module {
        const val dirPropertyName = "dms.module.parent-dirpath"
        lateinit var parentDir: File
        const val configFilename = "module.yml"
        val filenameRegex = "^dm-.+".toRegex()
        val zipFilenameRegex = "^dm-.+.zip$".toRegex()
    }
}