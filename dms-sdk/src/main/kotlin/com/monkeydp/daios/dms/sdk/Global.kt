package com.monkeydp.daios.dms.sdk

import com.github.javafaker.Faker
import com.monkeydp.daios.dms.sdk.datasource.exception.StdDatasourceException

/**
 * @author iPotato
 * @date 2019/10/23
 */
val faker = Faker()

fun dserror(cause: Throwable) {
    throw StdDatasourceException(cause)
}