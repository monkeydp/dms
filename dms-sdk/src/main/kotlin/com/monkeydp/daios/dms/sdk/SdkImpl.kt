package com.monkeydp.daios.dms.sdk

import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import kotlin.reflect.KClass

interface SdkImpl {
    
    val apis: Apis
    val classes: Classes
    val enumClasses: EnumClasses
    
    interface Apis {
        val connApi: ConnApi
        val nodeApi: NodeApi
        val menuApi: MenuApi
        val formApi: FormApi
        val instrApi: InstrApi
    }
    
    interface EnumClasses {
        val dsVersionClass: KClass<out DsVersion<*>>
        val actionClass: KClass<out Action<*>>
        val targetClass: KClass<out Target<*>>
        val iconClass: KClass<out Icon<*>>
    }
    
    interface Classes {
        val newConnFormClass: KClass<out NewConnForm>
    }
}