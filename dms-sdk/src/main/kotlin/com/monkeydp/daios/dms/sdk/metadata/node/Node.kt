package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Node {
    val target: Target<*>
    val name: String
    val icon: Icon<*>
}