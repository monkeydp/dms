package com.monkeydp.daios.dms.sdk.metadata

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType

/**
 * @author iPotato
 * @date 2019/10/25
 */
object MetadataEnumContext {
    
    val actionTypeMap = mutableMapOf<String, ActionType<*>>()
    val targetTypeMap = mutableMapOf<String, TargetType<*>>()
    
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
}