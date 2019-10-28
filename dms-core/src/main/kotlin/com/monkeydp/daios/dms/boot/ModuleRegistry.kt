package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.bundle.DmBundle
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/15
 */
@Component
class ModuleRegistry {
    
    private val dmBundleMap = mutableMapOf<Datasource, DmBundle>()
    
    fun registerModule(dmBundle: DmBundle) {
        dmBundleMap[dmBundle.datasource] = dmBundle
    }
    
    fun getDmBundle(datasource: Datasource) = dmBundleMap.get(datasource)!!
    
    fun getDmBundle(cp: ConnProfile) = getDmBundle(cp.datasource)
    
    fun getConnNodeDef(datasource: Datasource) = getDmBundle(datasource).connNd
}