package com.monkeydp.daios.dms.sdk.business.exception

import com.monkeydp.daios.dms.sdk.business.BusinessInfo

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class BusinessException(val info: BusinessInfo<*>) : Exception(info.message)