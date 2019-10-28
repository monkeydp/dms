package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractNd : NodeDef {
    override val parent: NodeDef? = null
    override val children = emptyList<NodeDef>()
}