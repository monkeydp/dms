package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf
import com.monkeydp.tools.ext.valueOf
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/5
 */
object InstrHelper {
    
    private const val DEFAULT_ACTION_REVERSE_INDEX = 2
    private const val DEFAULT_TARGET_REVERSE_INDEX = 1
    
    fun getInstrByClassname(any: Any,
                            actionReverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX,
                            targetReverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ) =
            try {
                val action = getActionByClassname(any, actionReverseIndex)
                val target = getTargetByClassname(any, targetReverseIndex)
                StdInstr(action, target)
            } catch (e: Exception) {
                UnknownInstr
            }
    
    fun getActionByClassname(any: Any, reverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX): Action<*> =
            getEnumByClassname(GlobalAction::class, any, reverseIndex)
    
    fun getTargetByClassname(any: Any, reverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX): Target<*> =
            getEnumByClassname(GlobalTarget::class, any, reverseIndex)
    
    private inline fun <reified E : Enum<E>> getEnumByClassname(enumClass: KClass<E>, any: Any, reverseIndex: Int): E {
        val clazz = if (any is Class<*>) any else any.javaClass
        val strs = clazz.simpleName.camelCase2List()
        val enumName = strs.lastOf(reverseIndex).toUpperCase()
        return enumClass.valueOf<E>(enumName)
    }
}