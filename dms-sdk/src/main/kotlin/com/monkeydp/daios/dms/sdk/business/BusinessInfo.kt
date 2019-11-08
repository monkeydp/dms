package com.monkeydp.daios.dms.sdk.business

import com.monkeydp.daios.dms.sdk.enumx.Enumx


/**
 * @author iPotato
 * @date 2019/10/25
 */
interface BusinessInfo<E> : Enumx<E>
        where E : BusinessInfo<E>, E : Enum<E> {
    val code: String
    val message: String
}