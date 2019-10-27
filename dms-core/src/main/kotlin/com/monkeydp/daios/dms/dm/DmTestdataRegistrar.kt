package com.monkeydp.daios.dms.dm

import com.monkeydp.daios.dms.sdk.dm.Dm

/**
 * @author iPotato
 * @date 2019/10/27
 */
internal object DmTestdataRegistrar {
    fun registerAll(testdata: Dm.Testdata) {
        DmTestdataRegistry.cps.addAll(testdata.cps)
    }
}