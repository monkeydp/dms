package com.monkeydp.daios.dms.sdk.metadata.icon

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Icon {
    
    val name: String
    
    /**
     * Hex
     */
    val color: String
    
    companion object {
        val DEFAULT_COLOR = "#979BA4" // grey
    }
}