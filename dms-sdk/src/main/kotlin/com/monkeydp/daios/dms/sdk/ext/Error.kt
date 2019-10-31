package com.monkeydp.daios.dms.sdk.ext

import com.monkeydp.daios.dms.sdk.business.BusinessInfo
import com.monkeydp.daios.dms.sdk.business.exception.StdBusinessException
import com.monkeydp.daios.dms.sdk.datasource.exception.StdDatasourceException

/**
 * @author iPotato
 * @date 2019/10/23
 */
fun derror(cause: Throwable) {
    throw StdDatasourceException(cause)
}

fun berror(info: BusinessInfo<*>) {
    throw StdBusinessException(info)
}