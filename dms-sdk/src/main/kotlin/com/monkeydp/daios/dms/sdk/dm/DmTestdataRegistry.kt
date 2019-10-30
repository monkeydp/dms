package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.conn.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.conn.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/27
 */
object DmTestdataRegistry {
    
    private val testDs = Datasource.valueOf(DATASOURCE)
    private val testDsVersion = DmImplRegistry.getDsVersion(testDs, DS_VERSION_ID)
    
    /**
     * Not in db, include all test cps of every dm
     */
    val cps = mutableListOf<ConnProfile>()
    
    val savedCpMap = mutableMapOf<DsVersion<*>, ConnProfile>()
    
    val testCp
        get() = savedCpMap.getValue(testDsVersion)
}