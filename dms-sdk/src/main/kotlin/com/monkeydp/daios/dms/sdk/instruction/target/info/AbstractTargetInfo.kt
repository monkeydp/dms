package com.monkeydp.daios.dms.sdk.instruction.target.info

import com.monkeydp.tools.ext.kotlin.notNullSingleton
import com.monkeydp.tools.ext.kotlin.toJson
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/7
 */
abstract class AbstractTargetInfo : TargetInfo {
    override var data: Any by Delegates.notNullSingleton()
    override fun toString() = if (data is String) data as String else data.toJson()
}