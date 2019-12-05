package com.monkeydp.daios.dms.sdk.ext

import com.monkeydp.daios.dms.sdk.business.BusinessInfo
import com.monkeydp.daios.dms.sdk.business.exception.BusinessException
import com.monkeydp.daios.dms.sdk.datasource.exception.DatasourceException

/**
 * @author iPotato
 * @date 2019/10/23
 */
fun berror(info: BusinessInfo<*>) {
    throw BusinessException(info)
}

fun dserror(cause: Throwable) {
    throw DatasourceException(cause)
}