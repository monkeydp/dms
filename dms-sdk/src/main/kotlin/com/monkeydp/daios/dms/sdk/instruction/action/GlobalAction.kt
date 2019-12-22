package com.monkeydp.daios.dms.sdk.instruction.action

import com.monkeydp.tools.ext.kotlin.toUpperCamelCase

/**
 * @author iPotato
 * @date 2019/10/25
 */
@SdkAction
enum class GlobalAction(fullName: String = "") : Action<GlobalAction> {
    NEW, EDIT, DELETE, OPEN, CLOSE, MANAGE, SHOW
    ;
    
    override val fullName: String
    
    init {
        this.fullName = (if (fullName.isBlank()) defaultFullName else fullName).toUpperCamelCase()
    }
}