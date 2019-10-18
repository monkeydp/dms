package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {

    val datasource: Datasource
    val dbDefs: List<DbDef>
    val implClassNames: ImplClassNames

    /**
     * Implementation class names of all sdk required contract
     */
    interface ImplClassNames {
        val connectionFactory: String?
    }

    /**
     * Database definition
     */
    interface DbDef {
        val version: DbVersion
        val driver: DbDriver
    }

    class DbVersion(val id: String, val name: String)
    class DbDriver(val id: String, val name: String)
}