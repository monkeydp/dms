package com.monkeydp.daios.dms.sdk.metadata.node.impl

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNode : Node {
    @JsonIgnore
    override val def: NodeDef
    override val target: Target<*>
    override val name: String
    override val icon: Icon<*>
    
    constructor(def: NodeDef) {
        this.def = def
        val info = def.info
        target = info.target
        name = info.name
        icon = info.icon
    }
}