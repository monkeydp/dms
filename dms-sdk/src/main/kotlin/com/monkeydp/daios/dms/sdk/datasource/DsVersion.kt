package com.monkeydp.daios.dms.sdk.datasource

import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.main.SdkEnumContract

/**
 * @author iPotato
 * @date 2019/10/27
 */
@SdkEnumContract
interface DsVersion<E> : Enumx<E>
        where E : DsVersion<E>, E : Enum<E> {
    val id: String
    val description: String
    val datasource: Datasource
}