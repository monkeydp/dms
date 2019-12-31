package com.monkeydp.daios.dms.sdk.datasource

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.daios.dms.sdk.config.kodein
import com.monkeydp.daios.dms.sdk.context.ConnContext
import com.monkeydp.daios.dms.sdk.context.ContextRepoHolder
import com.monkeydp.tools.enumeration.Symbol.DOT
import com.monkeydp.tools.ext.kodein.findImpl

/**
 * @author iPotato
 * @date 2019/12/28
 */
object DefaultDatasource {
    
    private val connContext: ConnContext get() = kodein.findImpl()
    
    fun get(): Datasource =
            when {
                ContextRepoHolder.hasContextRepo() -> connContext.datasource
                else -> {
                    Thread.currentThread().stackTrace
                            .first { dsOrNull(it.className) != null }
                            .let { dsOrNull(it.className)!! }
                }
            }
    
    private fun dsOrNull(className: String): Datasource? =
            dsNameOrNull(className).let { Datasource.valueOfOrNull(it) }
    
    private fun dsNameOrNull(className: String): String? =
            if (!className.startsWith(PackageName.dm)) null
            else className.removePrefix("${PackageName.dm}.").split(DOT).firstOrNull()
}