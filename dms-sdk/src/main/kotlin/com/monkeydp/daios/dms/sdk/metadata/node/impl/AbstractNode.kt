package com.monkeydp.daios.dms.sdk.metadata.node.impl

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNode(
        @JsonIgnore
        override val def: NodeDef
) : Node {
    override val target = def.info.target
    override val name = def.info.name
    override val icon: Icon<*> = def.info.icon
}