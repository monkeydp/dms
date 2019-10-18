package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.bundle.DmBundle
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/15
 */
@Component
class ModuleRegistry {

    val dmBundleMap: MutableMap<Datasource, DmBundle> = mutableMapOf()

    fun registerModule(dmBundle: DmBundle) {
        dmBundleMap[dmBundle.datasource] = dmBundle
    }

    fun getDmBundle(datasource: Datasource): DmBundle {
        return dmBundleMap.get(datasource)!!
    }
}