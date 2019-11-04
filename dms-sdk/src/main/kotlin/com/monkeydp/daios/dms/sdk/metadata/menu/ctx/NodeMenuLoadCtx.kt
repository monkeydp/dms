package com.monkeydp.daios.dms.sdk.metadata.menu.ctx

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuPath
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath

/**
 * @author iPotato
 * @date 2019/11/1
 */
data class NodeMenuLoadCtx(
        val nodePath: NodePath,
        val menuPath: MenuPath = MenuPath()
)