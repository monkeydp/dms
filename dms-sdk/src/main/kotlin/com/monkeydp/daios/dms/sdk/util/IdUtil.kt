package com.monkeydp.daios.dms.sdk.util

/**
 * @author iPotato
 * @date 2019/10/25
 */
object IdUtil {
    const val INVALID_ID = -1L
    fun isValid(id: Long) = id != INVALID_ID && id >= 1
}