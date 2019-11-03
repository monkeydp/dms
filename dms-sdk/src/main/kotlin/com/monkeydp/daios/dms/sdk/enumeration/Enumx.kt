package com.monkeydp.daios.dms.sdk.enumeration

import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Enumx<E>
        where E : Enumx<E>, E : Enum<E> {
    @Suppress("UNCHECKED_CAST")
    fun asEnum() = this as Enum<E>
    
    companion object {
        @Suppress("UNCHECKED_CAST")
        fun getContract(enumxClass: KClass<out Enumx<*>>): KClass<out Enumx<*>> {
            val interfaces = enumxClass.java.interfaces
            return if (interfaces.contains(Enumx::class.java)) enumxClass
            else interfaces.first { it.kotlin.isSubclassOf(Enumx::class) }.kotlin as KClass<Enumx<*>>
        }
    }
}