package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.api.ConnApi
import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDriver
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.def.ConnNd
import kotlin.reflect.KClass

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {
    
    val datasource: Datasource
    val connNd: ConnNd
    val dsDefs: List<DsDef>
    val impl: Impl
    val testdata: Testdata
    
    interface Impl {
    
        val apis: Apis
        val classes: Classes
        val enumClasses: EnumClasses
    
        interface Apis {
            val connApi: ConnApi
            val nodeApi: NodeApi
        }
    
        interface Classes {
            val cpFormClass: KClass<out CpForm>
        }
        
        interface EnumClasses {
            val dsVersionClass: Class<out DsVersion<*>>
            val actionClass: Class<out Action<*>>
            val targetClass: Class<out Target<*>>
            val iconClass: Class<out Icon<*>>
        }
    }
    
    /**
     * Datasource definition
     */
    interface DsDef {
        val version: DsVersion<*>
        val driver: DsDriver
    }
    
    interface Testdata {
        val cps: List<ConnProfile>
    }
}