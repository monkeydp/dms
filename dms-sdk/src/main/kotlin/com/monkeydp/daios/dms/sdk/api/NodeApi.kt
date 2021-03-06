package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.ui.node.ConnNode
import com.monkeydp.daios.dms.sdk.ui.node.Node

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi : Api {
    fun loadConnNodes(cps: Iterable<ConnProfile>): List<ConnNode>
    fun loadSubNodes(): List<Node>
}