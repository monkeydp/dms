package com.monkeydp.daios.dms.sdk.enumx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Enumx<E>
        where E : Enumx<E>, E : Enum<E> {
    @Suppress("UNCHECKED_CAST")
    fun asEnum() = this as Enum<E>
}