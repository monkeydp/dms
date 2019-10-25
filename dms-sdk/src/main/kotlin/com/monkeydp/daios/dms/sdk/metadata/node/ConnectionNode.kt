package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class ConnectionNode(
        override val def: NodeDef,
        override val info: NodeInfo,
        val cp: ConnectionProfile
) : AbstractNode(def, info) {

}