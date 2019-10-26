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
    val impl: Impl
    
    interface Impl {
        
        val apiClassnames: ApiClassnames
        val enumClassnames: EnumClassnames
        val dataClassnames: DataClassnames
        
        /**
         * Implementation api classnames of all sdk required contract
         */
        interface ApiClassnames {
            val connFactory: String
        }
        
        /**
         * Implementation enum classnames
         */
        interface EnumClassnames {
            val actionType: String
            val targetType: String
        }
        
        /**
         * Implementation data classnames
         */
        interface DataClassnames {
            val cpForm: String
        }
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