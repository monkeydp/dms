package com.monkeydp.daios.dms.sdk.datasource.exception

/**
 * @author iPotato
 * @date 2019/10/20
 */
abstract class AbstractDatasourceException : DatasourceException<AbstractDatasourceException>, RuntimeException {
    constructor(cause: Throwable) : super(cause)
}