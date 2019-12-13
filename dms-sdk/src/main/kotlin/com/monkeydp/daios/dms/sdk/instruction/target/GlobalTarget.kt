package com.monkeydp.daios.dms.sdk.instruction.target

import com.monkeydp.tools.ext.kotlin.toUpperCamelCase

/**
 * @author iPotato
 * @date 2019/10/25
 */
enum class GlobalTarget(fullName: String = "") : Target<GlobalTarget> {
    
    CONN("connection"),
    DB("database"),
    TABLE,
    VIEW,
    
    GROUP,
    
    QUERY,
    INFO("information")
    ;
    
    override val fullName: String
    
    init {
        this.fullName = (if (fullName.isBlank()) defaultFullName else fullName).toUpperCamelCase()
    }
}