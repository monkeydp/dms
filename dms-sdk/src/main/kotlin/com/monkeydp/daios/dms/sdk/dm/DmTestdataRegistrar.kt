package com.monkeydp.daios.dms.sdk.dm

/**
 * @author iPotato
 * @date 2019/10/27
 */
object DmTestdataRegistrar {
    fun registerAll(testdata: DmTestdata) {
        DmTestdataRegistry.cps.addAll(testdata.cps)
    }
}