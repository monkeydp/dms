package com.monkeydp.daios.dms.sdk.main

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
    
    interface Apis {
        val connApi: ConnApi<*>
        val nodeApi: NodeApi
        val menuApi: MenuApi
        val formApi: FormApi
        val instrApi: InstrApi
    }
    
    interface Classes {
        // Class
        val newConnFormKClass: KClass<out NewConnForm>
    
        // Enum class
        val dsVersionKClass: KClass<out DsVersion<*>>
        val actionKClass: KClass<out Action<*>>
        val targetKClass: KClass<out Target<*>>
        val iconKClass: KClass<out Icon<*>>
    }
}