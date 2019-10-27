package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * Instruction = Action + Target
 * @see
 * @author iPotato
 * @date 2019/10/25
 */
interface Instruction {
    
    val action: Action<*>
    val target: Target<*>
    
    fun isInstruction(action: Action<*>, target: Target<*>) =
            action == this.action && target == this.target
}