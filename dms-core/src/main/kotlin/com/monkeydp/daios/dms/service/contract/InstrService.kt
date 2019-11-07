package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx

/**
 * @author iPotato
 * @date 2019/11/1
 */
interface InstrService {
    /**
     * Return value should send by message exchange
     */
    fun parse(ctx: InstrParsingCtx)
}