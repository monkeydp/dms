package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnProfile

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