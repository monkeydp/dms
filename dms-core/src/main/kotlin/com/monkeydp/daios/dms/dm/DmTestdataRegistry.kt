package com.monkeydp.daios.dms.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource.MYSQL
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/27
 */
internal object DmTestdataRegistry {
    
    private val testDs = MYSQL
    private const val testDsVersionId = "5.7"
    private val testDsVersion = DmImplRegistry.getDsVersion(testDs, testDsVersionId)
    
    /**
     * Not in db
     */
    val cps = mutableListOf<ConnProfile>()
    
    val savedCpMap = mutableMapOf<DsVersion<*>, ConnProfile>()
    
    val testCp
        get() = savedCpMap[testDsVersion]!!
}