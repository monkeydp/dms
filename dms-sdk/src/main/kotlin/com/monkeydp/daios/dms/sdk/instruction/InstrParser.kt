package com.monkeydp.daios.dms.sdk.instruction

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrParser {
    fun parse(params: InstrParsingParams)
}