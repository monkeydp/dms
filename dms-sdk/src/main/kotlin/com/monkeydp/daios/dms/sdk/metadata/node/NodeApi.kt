package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.node.def.ConnNodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeApi {
    fun getConnNodeDef(): ConnNodeDef
    fun loadNodes(instr: NodeLoadInstr): List<Node>
}