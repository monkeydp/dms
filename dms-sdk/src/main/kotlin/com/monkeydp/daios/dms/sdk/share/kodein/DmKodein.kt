package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/9
 */
private val _dmKodeinMap = mutableMapOf<Datasource, Kodein>()
val dmKodeinMap get() = _dmKodeinMap.toMap()

fun putDmKodein(datasource: Datasource, dmKodein: Kodein) {
    _dmKodeinMap[datasource] = dmKodein
}

fun getDmKodein(datasource: Datasource) = _dmKodeinMap.getValue(datasource)