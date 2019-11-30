package com.monkeydp.daios.dms.sdk.datasource

/**
 * Datasource definition
 */
interface DsDef {
    val version: DsVersion<*>
    val driver: DsDriver
}