package com.monkeydp.daios.dms.sdk.metadata.node

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class NodeInfoPath : ArrayList<NodeInfo>() {
    companion object {
        fun of(vararg elements: NodeInfo): NodeInfoPath {
            val nip = NodeInfoPath()
            nip.addAll(elements)
            return nip
        }
    }
}