package com.monkeydp.daios.dms.sdk.metadata.instruction.ctx

import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.useful.UserInput

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrParseCtx {
    abstract val instr: Instruction
    abstract val userInput: UserInput
}