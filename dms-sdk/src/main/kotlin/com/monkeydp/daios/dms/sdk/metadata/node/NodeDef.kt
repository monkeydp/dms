package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeDef {
    val info: NodeInfo
    val children: List<NodeDef>
}