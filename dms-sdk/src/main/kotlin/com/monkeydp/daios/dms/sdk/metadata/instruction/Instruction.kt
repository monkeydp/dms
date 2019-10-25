package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType

/**
 * Instruction = Action + Target
 * @see
 * @author iPotato
 * @date 2019/10/25
 */
interface Instruction {
    
    val action: Action
    val target: Target
    
    fun isInstruction(actionType: ActionType<*>, targetType: TargetType<*>) =
            actionType == action.type && targetType == target.type
}