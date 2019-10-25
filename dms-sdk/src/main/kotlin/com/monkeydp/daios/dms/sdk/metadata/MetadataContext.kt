package com.monkeydp.daios.dms.sdk.metadata

import com.monkeydp.daios.dms.sdk.util.IdUtil
import com.monkeydp.daios.dms.sdk.util.IdUtil.INVALID_ID

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface MetadataContext {
    val globalInfo: GlobalInfo
    fun isValid() = IdUtil.isValid(globalInfo.cpId)
    data class GlobalInfo(val cpId: Long = INVALID_ID)
}