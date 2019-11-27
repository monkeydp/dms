package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.config.env.AbstractConfigEnvWrapper
import com.monkeydp.daios.dms.module.ModuleHelper
import org.springframework.core.env.ConfigurableEnvironment

/**
 * @author iPotato
 * @date 2019/11/27
 */
object PreparatoryWork {
    fun init(env: ConfigurableEnvironment) {
        AbstractConfigEnvWrapper.init(env)
        ModuleHelper.deployAllModules2Local()
    }
}