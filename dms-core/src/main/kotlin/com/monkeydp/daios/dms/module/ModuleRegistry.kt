package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/15
 */
@Component
class ModuleRegistry {
    
    private val dmBundleMap = mutableMapOf<Datasource, ModuleBundle>()
    
    fun registerModule(bundle: ModuleBundle) {
        dmBundleMap[bundle.datasource] = bundle
    }
    
    fun getBundle(datasource: Datasource) = dmBundleMap.get(datasource)!!
    
    fun getBundle(cp: ConnProfile) = getBundle(cp.datasource)
}