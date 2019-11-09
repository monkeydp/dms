package com.monkeydp.daios.dms.sdk.enumx

import com.monkeydp.tools.ext.getInterfaces
import com.monkeydp.tools.ext.hasAnnotation
import com.monkeydp.tools.ext.matchOne
import kotlin.reflect.KClass

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
        return interfaces.matchOne { it.hasAnnotation<SdkEnumContract>() }
    }
}