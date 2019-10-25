package com.monkeydp.daios.dms.sdk.business

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface BusinessInfo<E>
        where E : Enum<E>, E : BusinessInfo<E> {
    val code: String
    val message: String
    val asEnum: E
}