package com.monkeydp.daios.dms.sdk.datasource.exception

import com.monkeydp.tools.exception.GlobalException

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface DatasourceException<E> : GlobalException<E>
        where E : DatasourceException<E>, E : RuntimeException