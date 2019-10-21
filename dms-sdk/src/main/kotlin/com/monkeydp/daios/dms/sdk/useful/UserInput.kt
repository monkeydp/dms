package com.monkeydp.daios.dms.sdk.useful

import com.monkeydp.tools.util.JsonUtil
import org.jetbrains.annotations.TestOnly

/**
 * Parameters entered by the user
 *
 * @author iPotato
 * @date 2019/10/20
 */
class UserInput() : HashMap<String, Any>() {

    @TestOnly
    internal constructor(map: Map<String, Any>) : this() {
        this.putAll(map)
    }

    fun <T> convertTo(clazz: Class<T>): T {
        return JsonUtil.convertTo(this, clazz)
    }
}