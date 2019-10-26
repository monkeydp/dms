package com.monkeydp.daios.dms.sdk.metadata.node

import com.fasterxml.jackson.annotation.JsonIgnore

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
    override val icon = def.info.icon
}