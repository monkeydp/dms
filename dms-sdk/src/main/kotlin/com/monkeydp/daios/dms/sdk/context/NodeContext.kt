package com.monkeydp.daios.dms.sdk.context

import com.monkeydp.daios.dms.sdk.ui.node.NodePath

/**
 * @author iPotato
 * @date 2019/12/31
 */
interface NodeContext : Context {
    val path: NodePath
    
    companion object {
        operator fun invoke(path: NodePath): NodeContext = StdNodeContext(path)
    }
}

class StdNodeContext(
        override val path: NodePath
) : NodeContext