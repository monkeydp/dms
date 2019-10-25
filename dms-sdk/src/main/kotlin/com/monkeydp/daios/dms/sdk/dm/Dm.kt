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
    val implClassnames: ImplClassnames
    val implEnumClassnames: ImplEnumClassnames
    
    /**
     * Implementation classnames of all sdk required contract
     */
    interface ImplClassnames {
        val connectionFactory: String
    }
    
    /**
     * Implementation enum classnames
     */
    interface ImplEnumClassnames {
        val actionType: String
        val targetType: String
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