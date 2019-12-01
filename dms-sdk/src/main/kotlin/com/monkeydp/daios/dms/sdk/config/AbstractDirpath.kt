package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.tools.ext.toStdPath

/**
 * @author iPotato
 * @date 2019/11/30
 */
abstract class AbstractDirpath {
    val root = System.getProperty("user.dir").toStdPath()
    val src = "$root/src"
    val main = "$src/main"
    val build = "$root/build"
    val classes = "$build/classes"
    val generated = "$src/generated"
    val kotlinGenerated = "$generated/kotlin"
}