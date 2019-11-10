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
    
    private val bundleMap = mutableMapOf<Datasource, ModuleBundle>()
    
    fun registerModule(bundle: ModuleBundle) {
        bundleMap[bundle.datasource] = bundle
    }
    
    fun getBundle(datasource: Datasource) = bundleMap.get(datasource)!!
    
    fun getBundle(cp: ConnProfile) = getBundle(cp.datasource)
    
    private fun <V> toMap(apiPropName: String) =
            bundleMap.map { (ds, bundle) -> ds to bundle.apis.getPropValueX<V>(apiPropName) }.toMap()
    
    @Lazy
    @Bean
    fun connApiMap(): Map<Datasource, ConnApi> = toMap(Apis::connApi.name)
    
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