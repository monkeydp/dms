package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {

    val datasource: Datasource
    val dsDefs: List<DsDef>
    val implClassNames: ImplClassNames

    /**
     * Implementation class names of all sdk required contract
     */
    interface ImplClassNames {
        val connectionFactory: String?
    }

    /**
     * Datasource definition
     */
    interface DsDef {
        val version: DsVersion
        val driver: DsDriver
    }

    class DsDriver(val id: String, val classname: String)
}