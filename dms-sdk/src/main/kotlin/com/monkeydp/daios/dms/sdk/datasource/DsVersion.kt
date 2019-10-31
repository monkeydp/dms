package com.monkeydp.daios.dms.sdk.datasource

import com.monkeydp.daios.dms.sdk.enumeration.Enumx

/**
 * @author iPotato
 * @date 2019/10/27
 */
interface DsVersion<E> : Enumx<E>
        where E : DsVersion<E>, E : Enum<E> {
    val id: String
    val description: String
    val datasource: Datasource
}