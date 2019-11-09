package com.monkeydp.daios.dms.sdk.enumx

import com.monkeydp.tools.ext.getInterfaces
import com.monkeydp.tools.ext.ierror
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * @author iPotato
 * @date 2019/11/9
 */
object EnumxHelper {
    
    fun getEnumxContract(enumx: Enumx<*>) = getEnumxContract(enumx.javaClass.kotlin)
    
    @Suppress("UNCHECKED_CAST")
    fun getEnumxContract(enumxKClass: KClass<out Enumx<*>>) = innerGetEnumContract(enumxKClass) as KClass<out Enumx<*>>
    
    private fun innerGetEnumContract(enumKClass: KClass<*>): KClass<*> {
        val interfaces = mutableSetOf<KClass<*>>()
        if (enumKClass.java.isInterface) interfaces.add(enumKClass)
        interfaces.addAll(enumKClass.getInterfaces())
    
        val matched = interfaces.filter { it.findAnnotation<SdkEnumContract>() != null }
        if (matched.size != 1)
            ierror("""Matches contract 0 times or more then once
                      Enum class is: $enumKClass
                      Matching times: ${matched.size}
                      Following contracts are matched:
                      $matched
                    """)
        return matched.first()
    }
}