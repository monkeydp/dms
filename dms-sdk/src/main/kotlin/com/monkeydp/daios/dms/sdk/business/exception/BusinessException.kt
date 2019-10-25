package com.monkeydp.daios.dms.sdk.business.exception

import com.monkeydp.tools.exception.GlobalException

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface BusinessException<E> : GlobalException<E>
        where E : BusinessException<E>, E : RuntimeException