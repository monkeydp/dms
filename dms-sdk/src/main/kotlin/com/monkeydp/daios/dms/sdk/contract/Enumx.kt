package com.monkeydp.daios.dms.sdk.contract

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Enumx<E>
        where E : Enumx<E>, E : Enum<E> {
    val asEnum: E
}