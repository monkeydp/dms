package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.InstrParseCtx

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrApi {
    fun parse(ctx: InstrParseCtx)
}