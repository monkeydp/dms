package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.ext.getDatasourceByClassname
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/9
 */
private val dmKodeinMap = mutableMapOf<Datasource, Kodein>()

fun putDmKodein(any: Any, dmKodein: Kodein) =
        putDmKodein(any.getDatasourceByClassname(), dmKodein)

private fun putDmKodein(datasource: Datasource, dmKodein: Kodein) {
    dmKodeinMap[datasource] = dmKodein
}

fun getDmKodein(datasource: Datasource) = dmKodeinMap.getValue(datasource)