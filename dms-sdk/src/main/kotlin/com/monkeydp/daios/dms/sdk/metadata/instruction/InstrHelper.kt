package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.config.GlobalConfig.globalActionClass
import com.monkeydp.daios.dms.sdk.config.GlobalConfig.globalTargetClass
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf
import com.monkeydp.tools.ext.valueOf
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/5
 */
object InstrHelper {
    fun getActionByClassname(any: Any, reverseOffset: Int = 2) =
            getEnumByClassname(globalActionClass, any, reverseOffset)
    
    fun getTargetByClassname(any: Any, reverseOffset: Int = 1) =
            getEnumByClassname(globalTargetClass, any, reverseOffset)
    
    private inline fun <reified E : Enum<E>> getEnumByClassname(enumClass: KClass<E>, any: Any, reverseOffset: Int): E {
        val strs = any.javaClass.simpleName.camelCase2List()
        val enumName = strs.lastOf(reverseOffset).toUpperCase()
        return enumClass.valueOf<E>(enumName)
    }
}