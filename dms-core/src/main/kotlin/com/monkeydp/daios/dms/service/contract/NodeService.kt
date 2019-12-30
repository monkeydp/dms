package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.ui.node.ConnNode
import com.monkeydp.daios.dms.sdk.ui.node.Node
import com.monkeydp.daios.dms.sdk.ui.node.NodePath

/**
 * @author iPotato
 * @date 2019/10/28
 */
interface NodeService {
    fun loadConnNodes(): List<ConnNode>
    fun loadSubNodes(path: NodePath): List<Node>
}