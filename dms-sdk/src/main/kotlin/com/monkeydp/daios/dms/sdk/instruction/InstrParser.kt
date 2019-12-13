package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx
import com.monkeydp.daios.dms.sdk.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrParser {
    fun parse(ctx: InstrParsingCtx)
}