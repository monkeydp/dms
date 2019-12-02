package com.monkeydp.daios.dms.sdk.datasource

/**
 * Datasource definition
 */
interface DsDef {
    var version: DsVersion<*>
    var driver: DsDriver
}