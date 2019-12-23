package com.monkeydp.daios.dms.sdk.datasource

/**
 * Datasource definition
 */
interface DsDef {
    val version: DsVersion<*>
    
    companion object {
        operator fun invoke(version: DsVersion<*>): DsDef = StdDsDef(version)
    }
}

abstract class AbstractDsDef(override val version: DsVersion<*>) : DsDef

private class StdDsDef(version: DsVersion<*>) : AbstractDsDef(version)