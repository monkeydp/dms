package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class ConnNode(
        override val def: NodeDef,
        val cp: ConnectionProfile
) : AbstractNode(def) {
    override val name = cp.form.connName
}