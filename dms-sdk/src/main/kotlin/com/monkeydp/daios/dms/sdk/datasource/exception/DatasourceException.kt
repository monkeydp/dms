package com.monkeydp.daios.dms.sdk.datasource.exception

/**
 * @author iPotato
 * @date 2019/10/20
 */
open class DatasourceException : Exception {
    constructor(cause: Throwable) : super(cause)
}