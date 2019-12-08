package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.module.ModuleHelper

/**
 * @author iPotato
 * @date 2019/11/27
 */
object PreparatoryWork {
    fun init() {
        ModuleHelper.deployAllModules2Local()
    }
}