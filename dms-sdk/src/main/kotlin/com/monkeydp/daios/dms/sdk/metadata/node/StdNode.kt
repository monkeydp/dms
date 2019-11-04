package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/11/3
 */
class StdNode(
        target: Target<*>,
        name: String,
        icon: Icon<*>
) : AbstractNode(target, name, icon) {
    constructor(node: Node) : this(node.target, node.name, node.icon)
}