package com.monkeydp.daios.dms.sdk.metadata.icon

import com.monkeydp.daios.dms.sdk.contract.Enumx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Icon<E> : Enumx<E>
        where E : Icon<E>, E : Enum<E> {
    
    val namex: String
    
    /**
     * Hex
     */
    val color: String
    
    companion object {
        val DEFAULT_COLOR = "#979BA4" // grey
    }
}