package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.tools.ext.java.singletonX
import com.monkeydp.tools.ext.kotlin.*
import com.monkeydp.tools.ext.main.ierror
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/5
 */
object InstrHelper {
    
    private const val DEFAULT_ACTION_REVERSE_INDEX = 2
    private const val DEFAULT_TARGET_REVERSE_INDEX = 1
    
    /**
     * Get instruction by the given class name
     */
    fun getInstr(any: Any,
                 actionReverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX,
                 targetReverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ): Instruction {
        val action = getAction(any, actionReverseIndex)
        val target = getTarget(any, targetReverseIndex)
        return StdInstr(action, target)
    }
    
    fun getInstrOrNull(any: Any,
                       actionReverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX,
                       targetReverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ): Instruction? {
        val action = getActionOrNull(any, actionReverseIndex)
        val target = getTargetOrNull(any, targetReverseIndex)
        if (action == null || target == null) return null
        return StdInstr(action, target)
    }
    
    /**
     * Get action by the given class name
     */
    fun getAction(any: Any, reverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX): Action<*> =
            getEnum(GlobalAction::class, any, reverseIndex)
    
    fun getActionOrNull(any: Any, reverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX): Action<*>? =
            getEnumOrNull(GlobalAction::class, any, reverseIndex)
    
    /**
     * Get target by the given class name
     */
    fun getTarget(any: Any, reverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX): Target<*> =
            getEnum(GlobalTarget::class, any, reverseIndex)
    
    fun getTargetOrNull(any: Any, reverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX): Target<*>? =
            getEnumOrNull(GlobalTarget::class, any, reverseIndex)
    
    private inline fun <reified E : Enum<E>> getEnum(enumClass: KClass<E>, any: Any, reverseIndex: Int): E {
        val enumName = getEnumName(any.classX, reverseIndex)
        return enumClass.valueOf<E>(enumName)
    }
    
    private inline fun <reified E : Enum<E>> getEnumOrNull(enumClass: KClass<E>, any: Any, reverseIndex: Int): E? {
        val enumName = getEnumNameOrNull(any.classX, reverseIndex)
        if (enumName == null) return null
        return enumClass.valueOfOrNull<E>(enumName)
    }
    
    private fun getEnumName(clazz: Class<*>, reverseIndex: Int): String {
        val enumName = getEnumNameOrNull(clazz, reverseIndex)
        if (enumName == null) ierror("Cannot find enum name in ${clazz.simpleName}, class is: $clazz")
        return enumName
    }
    
    private fun getEnumNameOrNull(clazz: Class<*>, reverseIndex: Int): String? {
        val strings = clazz.simpleName.camelCase2List()
        if (strings.hasNoIndex(reverseIndex)) return null
        return strings.lastOf(reverseIndex).toUpperCase()
    }
    
    /**
     * Get instruction by it's kClass
     */
    fun getInstr(instrKClass: KClass<out Instruction>) = instrKClass.java.singletonX()
    
    private fun getInstr(instrKClass: KClass<out Instruction>, kClass: KClass<*>) =
            if (instrKClass != Nothing::class) getInstr(instrKClass)
            else getInstr(kClass)
    
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