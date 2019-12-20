package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodeinHelper
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import com.monkeydp.tools.useful.SourceSet
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/10
 */
object ModuleTestdata {
    private val dsVersion get() = DmKodeinHelper.findDsVersion(Datasource.valueOf(DATASOURCE), DS_VERSION_ID)
    val cps: Iterable<ConnProfile> get() = DmKodeinHelper.finalAllImpls<Iterable<ConnProfile>>(SourceSet.TEST).flatten()
    var savedCps: List<ConnProfile> by Delegates.notNullSingleton()
    val cp get() = savedCps.matchOne { it.dsVersion == dsVersion }
}