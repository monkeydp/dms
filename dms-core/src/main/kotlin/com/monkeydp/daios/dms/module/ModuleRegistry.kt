package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component


/**
 * @author iPotato
 * @date 2019/10/15
 */
@Component
class ModuleRegistry {
    
    private val moduleMap = mutableMapOf<Datasource, Module>()
    
    fun registerModule(module: Module) {
        moduleMap[module.datasource] = module
    }
    
    fun findModule(datasource: Datasource) = moduleMap.get(datasource)!!
    
    fun findModule(cp: ConnProfile) = findModule(cp.datasource)
    
    private inline fun <reified T : Any> buildImplMap() =
            moduleMap.map { (ds, module) ->
                ds to module.findImpl<T>()
            }.toMap()
    
    @Lazy
    @Bean
    fun connApiMap() = buildImplMap<ConnApi>()
    
    @Lazy
    @Bean
    fun nodeApiMap() = buildImplMap<NodeApi>()
    
    @Lazy
    @Bean
    fun menuApiMap() = buildImplMap<MenuApi>()
    
    @Lazy
    @Bean
    fun formApiMap() = buildImplMap<FormApi>()
    
    @Lazy
    @Bean
    fun instrApiMap() = buildImplMap<InstrApi>()
}