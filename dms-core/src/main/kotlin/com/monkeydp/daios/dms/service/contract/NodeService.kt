package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath

/**
 * @author iPotato
 * @date 2019/10/28
 */
interface NodeService {
    fun loadConnNodes(): List<ConnNode>
    fun loadSubNodes(path: NodePath): List<Node>
}