package com.monkeydp.daios.dms.sdk.event

import com.monkeydp.daios.dms.sdk.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/11/6
 */
abstract class AbstractInstrEvent(
        source: Any,
        notifyUi: Boolean = true,
        instr: Instruction? = null
) : InstrEvent, AbstractEvent(source, notifyUi) {
    override val instr: Instruction = instr ?: InstrHelper.getInstr(this)
    
    override fun toString() = "[EVENT] ${instr.toString()}"
}