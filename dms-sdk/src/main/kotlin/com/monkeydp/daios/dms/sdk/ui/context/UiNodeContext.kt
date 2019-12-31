package com.monkeydp.daios.dms.sdk.ui.context

import com.monkeydp.daios.dms.sdk.ui.node.NodePath

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface UiNodeContext : UiContext {
    val path: NodePath
    
    companion object {
        operator fun invoke(path: NodePath): UiNodeContext = StdUiNodeContext(path)
    }
}

class StdUiNodeContext(
        override val path: NodePath
) : UiNodeContext