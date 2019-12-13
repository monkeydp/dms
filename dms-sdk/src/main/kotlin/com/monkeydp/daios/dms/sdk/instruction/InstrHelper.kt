package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.tools.ext.java.singletonX
import com.monkeydp.tools.ext.kotlin.camelCase2List
import com.monkeydp.tools.ext.kotlin.lastOf
import com.monkeydp.tools.ext.kotlin.valueOf
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
    ): Instruction =
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
        val clazz =
                when (any) {
                    is Class<*> -> any
                    is KClass<*> -> any.java
                    else -> any.javaClass
                }
        val strs = clazz.simpleName.camelCase2List()
        val enumName = strs.lastOf(reverseIndex).toUpperCase()
        return enumClass.valueOf<E>(enumName)
    }
    
    fun getInstr(instrKClass: KClass<out Instruction>) = instrKClass.java.singletonX()
    
    private fun getInstr(instrKClass: KClass<out Instruction>, kClass: KClass<*>) =
            if (instrKClass != Nothing::class) getInstr(instrKClass)
            else getInstrByClassname(kClass)
    
    fun getInstr(instrKClass: KClass<out Instruction>, any: Any) =
            getInstr(
                    instrKClass,
                    when (any) {
                        is Class<*> -> any.kotlin
                        is KClass<*> -> any
                        else -> any::class
                    }
            )
}