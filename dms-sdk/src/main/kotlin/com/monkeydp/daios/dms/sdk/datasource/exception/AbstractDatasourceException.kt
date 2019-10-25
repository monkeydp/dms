package com.monkeydp.daios.dms.sdk.datasource.exception

import com.monkeydp.tools.exception.AbstractGlobalException

/**
 * @author iPotato
 * @date 2019/10/20
 */
abstract class AbstractDatasourceException : AbstractGlobalException {
    constructor(cause: Throwable) : super(cause)
}