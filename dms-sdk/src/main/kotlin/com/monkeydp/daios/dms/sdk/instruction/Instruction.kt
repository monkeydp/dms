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
}

fun instruction(
        action: Action<*>,
        target: Target<*>
): Instruction = StdInstr(action, target)