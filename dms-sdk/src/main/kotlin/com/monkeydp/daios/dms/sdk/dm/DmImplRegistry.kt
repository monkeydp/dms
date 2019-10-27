package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType

/**
 * @author iPotato
 * @date 2019/10/25
 */
internal object DmImplRegistry {
    
    val actionTypeMap = mutableMapOf<String, ActionType<*>>()
    val targetTypeMap = mutableMapOf<String, TargetType<*>>()
    
    val cpFormClassMap = mutableMapOf<Datasource, Class<out CpForm>>()
    
    fun registerEnum(enum: Enum<*>, datasource: Datasource? = null) {
        val name = enum.name
        datasource?.checkPrefix(name)
        when (enum) {
            is ActionType<*> -> actionTypeMap.putIfAbsent(name, enum)
            is TargetType<*> -> targetTypeMap.putIfAbsent(name, enum)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    fun registerClass(clazz: Class<*>, datasource: Datasource) {
        if (CpForm::class.java.isAssignableFrom(clazz)) cpFormClassMap.putIfAbsent(datasource, clazz as Class<CpForm>)
    }
}