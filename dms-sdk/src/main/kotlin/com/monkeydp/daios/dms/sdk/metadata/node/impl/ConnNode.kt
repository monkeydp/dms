package com.monkeydp.daios.dms.sdk.metadata.node.impl

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class ConnNode(
        override val def: NodeDef,
        val cp: ConnProfile
) : AbstractNode(def) {
    override val name = cp.form.connName
}