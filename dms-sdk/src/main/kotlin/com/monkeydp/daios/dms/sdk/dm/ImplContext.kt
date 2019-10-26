package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType

/**
 * @author iPotato
 * @date 2019/10/25
 */
object ImplContext {
    
    val actionTypeMap = mutableMapOf<String, ActionType<*>>()
    val targetTypeMap = mutableMapOf<String, TargetType<*>>()
    
    val cpFormClassMap = mutableMapOf<Datasource, Class<out CpForm>>()
    
    fun registerEnum(actionType: ActionType<*>, datasource: Datasource? = null) {
        val name = actionType.asEnum().name
        datasource?.checkPrefix(name)
        actionTypeMap.putIfAbsent(name, actionType)
    }
    
    fun registerEnum(targetType: TargetType<*>, datasource: Datasource? = null) {
        val name = targetType.asEnum().name
        datasource?.checkPrefix(name)
        targetTypeMap.putIfAbsent(name, targetType)
    }
    
    fun registerDataClass(cpFormClass: Class<out CpForm>, datasource: Datasource) {
        cpFormClassMap.putIfAbsent(datasource, cpFormClass)
    }
}