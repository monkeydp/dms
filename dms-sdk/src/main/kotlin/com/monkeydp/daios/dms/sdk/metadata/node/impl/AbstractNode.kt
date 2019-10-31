package com.monkeydp.daios.dms.sdk.metadata.node.impl

import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNode : Node {
    override val info: NodeInfo
    
    constructor(def: NodeDef, name: String = def.info.name) {
        info = def.info.copy(name = name)
    }
}