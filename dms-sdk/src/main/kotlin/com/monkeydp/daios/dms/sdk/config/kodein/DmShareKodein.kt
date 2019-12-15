package com.monkeydp.daios.dms.sdk.config.kodein

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.ext.getDatasourceByClassname
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/9
 */
private val _dmShareKodeinMap = mutableMapOf<Datasource, Kodein>()
val dmShareKodeinMap get() = _dmShareKodeinMap.toMap()

fun putDmShareKodein(any: Any, dmKodein: Kodein) =
        putDmShareKodein(any.getDatasourceByClassname(), dmKodein)

private fun putDmShareKodein(datasource: Datasource, dmKodein: Kodein) {
    _dmShareKodeinMap[datasource] = dmKodein
}

fun getDmShareKodein(datasource: Datasource) = _dmShareKodeinMap.getValue(datasource)