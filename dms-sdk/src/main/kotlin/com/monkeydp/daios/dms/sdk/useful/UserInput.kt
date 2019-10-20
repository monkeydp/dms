package com.monkeydp.daios.dms.sdk.useful

import com.monkeydp.tools.util.JsonUtil

/**
 * Parameters entered by the user
 *
 * @author iPotato
 * @date 2019/10/20
 */
class UserInput(private val map: Map<String, Any>) {
    fun <T> convertTo(clazz: Class<T>): T {
        return JsonUtil.convertTo(map, clazz)
    }
}