package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import java.util.*

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Node {
    val defId: Int
    val target: Target<*>
    val name: String
    val icon: Icon<*>
    val childTargets: List<Target<*>>
}