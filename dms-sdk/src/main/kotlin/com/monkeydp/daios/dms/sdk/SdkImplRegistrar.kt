package com.monkeydp.daios.dms.sdk

import com.monkeydp.daios.dms.sdk.SdkImplRegistry.registerClasses
import com.monkeydp.daios.dms.sdk.datasource.Datasource

/**
 * @author iPotato
 * @date 2019/10/27
 */
object SdkImplRegistrar {
    
    fun registerAll(impl: SdkImpl, datasource: Datasource) {
        registerClasses(impl.classes, datasource)
    }
}