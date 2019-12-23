package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Node {
    val defId: Int
    val target: Target<*>
    val name: String
    val icon: Icon<*>
    
    companion object {
        operator fun invoke(
                defId: Int,
                target: Target<*>,
                name: String,
                icon: Icon<*>
        ): Node = StdNode(defId, target, name, icon)
    }
}

abstract class AbstractNode(
        override val defId: Int,
        override val target: Target<*>,
        override val name: String,
        override val icon: Icon<*>
) : Node

class StdNode(
        defId: Int,
        target: Target<*>,
        name: String,
        icon: Icon<*>
) : AbstractNode(defId, target, name, icon)