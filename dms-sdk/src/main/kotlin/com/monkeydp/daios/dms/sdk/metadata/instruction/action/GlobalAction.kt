package com.monkeydp.daios.dms.sdk.metadata.instruction.action

import com.monkeydp.tools.ext.toUpperCamelCase

/**
 * @author iPotato
 * @date 2019/10/25
 */
enum class GlobalAction(fullName: String = "") : Action<GlobalAction> {
    NEW, EDIT, DELETE, OPEN, CLOSE, MANAGE;
    
    override val fullName: String
    
    init {
        this.fullName = (if (fullName.isBlank()) defaultFullName else fullName).toUpperCamelCase()
    }
}