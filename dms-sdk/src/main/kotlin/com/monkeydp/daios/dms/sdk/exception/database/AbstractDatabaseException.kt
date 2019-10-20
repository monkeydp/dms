package com.monkeydp.daios.dms.sdk.exception.database

import com.monkeydp.tools.exception.AbstractGlobalException

/**
 * @author iPotato
 * @date 2019/10/20
 */
abstract class AbstractDatabaseException : AbstractGlobalException {
    constructor(cause: Throwable) : super(cause)
}