package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.api.contract.ConnApi
import com.monkeydp.daios.dms.sdk.api.contract.NodeApi
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import kotlin.reflect.KClass

interface DmImpl {
    
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
        val dsVersionClass: KClass<out DsVersion<*>>
        val actionClass: KClass<out Action<*>>
        val targetClass: KClass<out Target<*>>
        val iconClass: KClass<out Icon<*>>
    }
}