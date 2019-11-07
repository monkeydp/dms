package com.monkeydp.daios.dms.sdk.event

import com.monkeydp.daios.dms.sdk.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/11/7
 */
interface InstrEvent : Event {
    val instr: Instruction
}