package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/27
 */
object DmTestdataRegistry {
    
    var testDsVersion by Delegates.notNullSingleton<DsVersion<*>>()
    
    /**
     * Not in db, include all test cps of every dm
     */
    val cps = mutableListOf<ConnProfile>()
    
    val savedCpMap = mutableMapOf<DsVersion<*>, ConnProfile>()
    
    val testCp
        get() = savedCpMap.getValue(testDsVersion)
}