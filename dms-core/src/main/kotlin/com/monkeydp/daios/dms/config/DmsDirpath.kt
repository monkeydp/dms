package com.monkeydp.daios.dms.config

import com.monkeydp.tools.useful.AbstractDirpath

/**
 * @author iPotato
 * @date 2019/11/30
 */
object DmsDirpath : AbstractDirpath() {
    val dm = "$root/../dm"
    val ignore = "$root/../ignore"
    val modules = "$ignore/modules"
}