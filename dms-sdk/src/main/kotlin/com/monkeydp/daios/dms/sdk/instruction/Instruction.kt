package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target

/**
 * Instruction = Action + Target
 * @see
 * @author iPotato
 * @date 2019/10/25
 */
interface Instruction {
    val action: Action<*>
    val target: Target<*>
    
    companion object {
        operator fun invoke(
                action: Action<*>,
                target: Target<*>
        ): Instruction = StdInstr(action, target)
    }
}

abstract class AbstractInstr : Instruction {
    
    override val action: Action<*> by lazy { InstrHelper.getActionByClassname(this, 1) }
    override val target: Target<*> by lazy { InstrHelper.getTargetByClassname(this, 0) }
    
    override fun equals(other: Any?) =
            other is Instruction &&
            this.action == other.action &&
            this.target == other.target
    
    override fun hashCode(): Int {
        var result = 17
        result = result * 31 + action.hashCode()
        result = result * 31 + target.hashCode()
        return result
    }
    
    override fun toString() = "${action.fullName} ${target.fullName}"
}

class StdInstr(
        override val action: Action<*>,
        override val target: Target<*>
) : AbstractInstr()