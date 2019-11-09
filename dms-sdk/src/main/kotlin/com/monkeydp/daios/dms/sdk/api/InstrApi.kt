package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx

/**
 * @author iPotato
 * @date 2019/11/5
 */
@SdkApiContract
interface InstrApi {
    fun parse(ctx: InstrParsingCtx)
}