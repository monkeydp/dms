package com.monkeydp.daios.dms.sdk.helper

import com.monkeydp.tools.util.RandomUtil
import org.jetbrains.annotations.TestOnly

/**
 * @author iPotato
 * @date 2019/10/25
 */
object IdHelper {
    const val INVALID_ID = -1L
    fun isValid(id: Long) = id != INVALID_ID && id >= 1
    
    @TestOnly
    fun randomId(min: Int = 1, max: Int = 1000): Long = RandomUtil.randomInt(min, max).toLong()
}