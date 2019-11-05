package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/11/4
 */
class StdInstr : Instruction {
    override val action: Action<*>
    override val target: Target<*>
    
    constructor(action: Action<*>, target: Target<*>) : super() {
        this.action = action
        this.target = target
    }
}