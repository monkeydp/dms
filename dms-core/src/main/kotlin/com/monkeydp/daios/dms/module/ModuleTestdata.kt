package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.DmHelper
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.tools.ext.matchOne
import com.monkeydp.tools.ext.notNullSingleton
import com.monkeydp.tools.kodein.KodeinTag.TEST
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/10
 */
object ModuleTestdata {
    private val dsVersion get() = DmHelper.findDsVersion(Datasource.valueOf(DATASOURCE), DS_VERSION_ID)
    val cps get() = DmHelper.finalAllImpls<Set<ConnProfile>>(TEST).flatten()
    var savedCps: List<ConnProfile> by Delegates.notNullSingleton()
    val cp get() = savedCps.matchOne { it.dsVersion == dsVersion }
}