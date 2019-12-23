package com.monkeydp.daios.dms.sdk.instruction.target.info

import com.monkeydp.tools.ext.kotlin.toJson

/**
 * @author iPotato
 * @date 2019/11/7
 */
interface TargetInfo {
    val data: Any
    
    companion object {
        operator fun invoke(data: Any): TargetInfo = StdTargetInfo(data)
    }
}

abstract class AbstractTargetInfo(override val data: Any) : TargetInfo {
    override fun toString() = if (data is String) data as String else data.toJson()
}

private class StdTargetInfo(data: Any) : AbstractTargetInfo(data)