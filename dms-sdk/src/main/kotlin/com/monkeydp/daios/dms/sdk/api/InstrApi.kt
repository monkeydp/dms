package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.instruction.InstrParsingParams

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrApi : Api {
    fun parse(params: InstrParsingParams)
}