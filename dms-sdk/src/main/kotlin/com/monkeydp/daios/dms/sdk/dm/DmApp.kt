package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource

/**
 * @author iPotato
 * @date 2019/12/4
 */
interface DmApp {
    val datasource: Datasource
}