package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import org.springframework.stereotype.Component
import kotlin.properties.Delegates


/**
 * @author iPotato
 * @date 2019/10/15
 */
@Component
class ModuleRegistry {
    
    private var moduleMap: Map<Datasource, Module> by Delegates.notNullSingleton()
    
    fun registerAllModule(modules: List<Module>) {
        moduleMap = modules.map { it.datasource to it }.toMap()
    }
    
    fun findModule(datasource: Datasource) = moduleMap.getValue(datasource)
    
    fun findModule(cp: ConnProfile) = findModule(cp.datasource)
}