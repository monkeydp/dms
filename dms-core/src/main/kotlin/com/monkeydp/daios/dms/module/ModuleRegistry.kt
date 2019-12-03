package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.main.SdkImpl.Apis
import com.monkeydp.tools.ext.getPropValueX
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
    
    @Autowired
    private lateinit var beanFactory: ConfigurableBeanFactory
    
    private val moduleMap = mutableMapOf<Datasource, Module>()
    
    fun registerModule(module: Module) {
        moduleMap[module.datasource] = module
    }
    
    fun findModule(datasource: Datasource) = moduleMap.get(datasource)!!
    
    fun findModule(cp: ConnProfile) = findModule(cp.datasource)
    
    private fun <V> toMap(apiPropName: String) =
            moduleMap.map { (ds, module) -> ds to module.apis.getPropValueX<V>(apiPropName) }.toMap()
    
    @Lazy
    @Bean
    fun connApiMap(): Map<Datasource, ConnApi<ConnProfile>> = toMap(Apis::connApi.name)
    
    @Lazy
    @Bean
    fun nodeApiMap(): Map<Datasource, NodeApi> = toMap(Apis::nodeApi.name)
    
    @Lazy
    @Bean
    fun menuApiMap(): Map<Datasource, MenuApi> = toMap(Apis::menuApi.name)
    
    @Lazy
    @Bean
    fun formApiMap(): Map<Datasource, FormApi> = toMap(Apis::formApi.name)
    
    @Lazy
    @Bean
    fun instrApiMap(): Map<Datasource, InstrApi> = toMap(Apis::instrApi.name)
}