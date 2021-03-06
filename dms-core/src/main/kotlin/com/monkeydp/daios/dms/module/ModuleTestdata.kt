package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findFlatAllImpls
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.tools.ext.kotlin.singleton
import com.monkeydp.tools.useful.SourceSet
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/10
 */
object ModuleTestdata {
    private val dsVersion get() = dmKodeinRepo.findDsVersion(Datasource.valueOf(DATASOURCE), DS_VERSION_ID)
    val cps: List<ConnProfile> get() = dmKodeinRepo.findFlatAllImpls(SourceSet.TEST)
    var savedCps: List<ConnProfile> by Delegates.singleton()
    val cp get() = savedCps.single() { it.dsVersion == dsVersion }
}