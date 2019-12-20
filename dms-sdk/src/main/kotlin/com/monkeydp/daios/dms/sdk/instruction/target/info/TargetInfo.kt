package com.monkeydp.daios.dms.sdk.instruction.target.info

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/7
 */
interface TargetInfo {
    var data: Any
}

fun targetInfo(init: TargetInfo.() -> Unit): TargetInfo = initInstance<StdTargetInfo>(init)