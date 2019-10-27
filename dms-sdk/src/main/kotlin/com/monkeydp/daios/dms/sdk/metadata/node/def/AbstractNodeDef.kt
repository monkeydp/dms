package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractNodeDef : NodeDef {
    override val parent: NodeDef? = null
    override val children = mutableListOf<NodeDef>()
}