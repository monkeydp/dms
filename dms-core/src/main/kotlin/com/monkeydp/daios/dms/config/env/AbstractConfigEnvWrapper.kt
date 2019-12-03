package com.monkeydp.daios.dms.config.env

import com.monkeydp.tools.ext.camelCaseFirst
import com.monkeydp.tools.ext.notNullSingleton
import org.springframework.core.env.ConfigurableEnvironment
import kotlin.properties.Delegates

/**
 * ConfigEnvWrapper can be used before beans loaded, such as following bean
 * @author iPotato
 * @date 2019/11/27
 */
abstract class AbstractConfigEnvWrapper {
    
    companion object {
        private var env by Delegates.notNullSingleton<ConfigurableEnvironment>()
        fun init(env: ConfigurableEnvironment) {
            AbstractConfigEnvWrapper.env = env
        }
    }
    
    private val prefix: String?
        get() = javaClass.simpleName.camelCaseFirst().decapitalize()
    
    protected fun getProperty(propertyName: String) = env.getProperty(fullPropertyName(propertyName))!!
    
    private fun fullPropertyName(propertyName: String): String {
        val builder = StringBuilder()
        if (prefix?.isNotEmpty() == true)
            builder.append(prefix)
                    .append(".")
        builder.append(propertyName)
        return builder.toString()
    }
}