package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractInstr : Instruction {
    override val action: Action<*> by lazy {
        val simpleName = this.javaClass.simpleName
        val strs = simpleName.camelCase2List()
        GlobalAction.valueOf(strs.lastOf(2).toUpperCase())
    }
    override val target: Target<*> by lazy {
        val simpleName = this.javaClass.simpleName
        val strs = simpleName.camelCase2List()
        GlobalTarget.valueOf(strs.lastOf(1).toUpperCase())
    }
    
    override fun equals(other: Any?) =
            other is Instruction &&
            this.action == other.action &&
            this.target == other.target
}