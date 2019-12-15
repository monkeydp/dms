package com.monkeydp.daios.dms.sdk.ext

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.tools.ext.kotlin.camelCaseFirst

/**
 * @author iPotato
 * @date 2019/12/9
 */
fun Any.getDsNameByClassname() = javaClass.simpleName.camelCaseFirst()

fun Any.getDatasourceByClassname() = Datasource.valueOf(getDsNameByClassname().toUpperCase())

fun Any.getDmKodein() =
        com.monkeydp.daios.dms.sdk.config.kodein.getDmShareKodein(getDatasourceByClassname())