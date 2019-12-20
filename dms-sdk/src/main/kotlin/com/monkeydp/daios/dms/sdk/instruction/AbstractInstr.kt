package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

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
    
    override fun hashCode(): Int {
        var result = 17
        result = result * 31 + action.hashCode()
        result = result * 31 + target.hashCode()
        return result
    }
    
    override fun toString() = "${action.fullName} ${target.fullName}"
}