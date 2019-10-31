package com.monkeydp.daios.dms.sdk.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDriver
import com.monkeydp.daios.dms.sdk.datasource.DsVersion

/**
 * Datasource definition
 */
interface DsDef {
    val version: DsVersion<*>
    val driver: DsDriver
}