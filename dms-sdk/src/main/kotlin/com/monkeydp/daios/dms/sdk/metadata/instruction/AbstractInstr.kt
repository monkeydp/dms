package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractInstr : Instruction {
    
    override val action: Action<*> by lazy { InstrHelper.getActionByClassname(this, 1) }
    override val target: Target<*> by lazy { InstrHelper.getTargetByClassname(this, 0) }
    
    override fun equals(other: Any?) =
            other is Instruction &&
            this.action == other.action &&
            this.target == other.target
    
    override fun toString() = "${action.fullName} ${target.fullName}"
}