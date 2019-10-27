package com.monkeydp.daios.dms.sdk.datasource

/**
 * @author iPotato
 * @date 2019/10/27
 */
interface DsVersion<E> where E : DsVersion<E>, E : Enum<E> {
    val id: String
    val description: String
    val datasource: Datasource
}