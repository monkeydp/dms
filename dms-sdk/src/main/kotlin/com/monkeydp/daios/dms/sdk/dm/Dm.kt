package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.conn.ConnFactory
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType

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
    
        val apis: Apis
        val classes: Classes
        val enumClasses: EnumClasses
    
        interface Apis {
            val connFactory: ConnFactory
        }
    
        interface Classes {
            val cpFormClass: Class<out CpForm>
        }
        
        interface EnumClasses {
            val actionTypeClass: Class<out ActionType<*>>
            val targetTypeClass: Class<out TargetType<*>>
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