package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/10/31
 */
object UnknownInstr : AbstractInstr() {
    override val action = GlobalAction.UNKNOWN_ACTION
    override val target = GlobalTarget.UNKNOWN_TARGET
}