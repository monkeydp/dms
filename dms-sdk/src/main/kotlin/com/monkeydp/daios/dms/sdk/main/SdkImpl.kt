package com.monkeydp.daios.dms.sdk.main

import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.DmTestdata
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import kotlin.reflect.KClass

interface SdkImpl {
    
    // should be protected, but it's used in inline fun
    val kodein: Kodein
    
    val dsDefSet: Set<DsDef>
    val classes: Classes
    
    val testdata: DmTestdata
    
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

inline fun <reified T : Any> SdkImpl.findImpl(): T {
    val api: T by kodein.instance()
    return api
}