package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDef

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {
    val datasource: Datasource
    val dsDefs: List<DsDef>
    val impl: SdkImpl
    val testdata: DmTestdata
}