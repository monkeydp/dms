package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
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
    
    fun getInstrByClassname(
            any: Any,
            actionReverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX,
            targetReverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ): Instruction {
        val action = getActionByClassname(any, actionReverseIndex)
        val target = getTargetByClassname(any, targetReverseIndex)
        return StdInstr(action, target)
    }
    
    fun getInstrOrNull(
            any: Any,
            actionReverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX,
            targetReverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ): Instruction? {
        val action = getActionOrNullByClassname(any, actionReverseIndex)
        val target = getTargetOrNullByClassname(any, targetReverseIndex)
        if (action == null || target == null) return null
        return StdInstr(action, target)
    }
    
    fun getActionByClassname(
            any: Any,
            reverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX
    ): Action<*> = getEnumByClassname(GlobalAction::class, any, reverseIndex)
    
    fun getActionOrNullByClassname(
            any: Any,
            reverseIndex: Int = DEFAULT_ACTION_REVERSE_INDEX
    ): Action<*>? = getEnumOrNullByClassname(GlobalAction::class, any, reverseIndex)
    
    fun getTargetByClassname(
            any: Any,
            reverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ): Target<*> = getEnumByClassname(GlobalTarget::class, any, reverseIndex)
    
    fun getTargetOrNullByClassname(
            any: Any,
            reverseIndex: Int = DEFAULT_TARGET_REVERSE_INDEX
    ): Target<*>? = getEnumOrNullByClassname(GlobalTarget::class, any, reverseIndex)
    
    private inline fun <reified E : Enum<E>> getEnumByClassname(enumClass: KClass<E>, any: Any, reverseIndex: Int): E {
        val enumName = getEnumNameByClassname(any.classX, reverseIndex)
        return enumClass.valueOf<E>(enumName)
    }
    
    private inline fun <reified E : Enum<E>> getEnumOrNullByClassname(enumClass: KClass<E>, any: Any,
                                                                      reverseIndex: Int): E? {
        val enumName = getEnumNameOrNullByClassname(any.classX, reverseIndex)
        if (enumName == null) return null
        return enumClass.valueOfOrNull<E>(enumName)
    }
    
    private fun getEnumNameByClassname(clazz: Class<*>, reverseIndex: Int): String {
        val enumName = getEnumNameOrNullByClassname(clazz, reverseIndex)
        if (enumName == null) ierror("Cannot find enum name in ${clazz.simpleName}, class is: $clazz")
        return enumName
    }
    
    private fun getEnumNameOrNullByClassname(clazz: Class<*>, reverseIndex: Int): String? {
        val strings = clazz.simpleName.camelCase2List()
        if (strings.hasNoIndex(reverseIndex)) return null
        return strings.lastOf(reverseIndex).toUpperCase()
    }
    
    fun getInstr(instrKClass: KClass<out Instruction>, any: Any) =
            if (instrKClass != Nothing::class) instrKClass.singletonX()
            else getInstrByClassname(any.kClassX)
}