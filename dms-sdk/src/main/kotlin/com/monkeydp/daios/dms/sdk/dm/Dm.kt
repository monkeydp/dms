package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.main.SdkImpl

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {
    val datasource: Datasource
    val dsDefs: Iterable<DsDef>
    val impl: SdkImpl
    val testdata: DmTestdata
}