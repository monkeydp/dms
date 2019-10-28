package com.monkeydp.daios.dms.sdk.metadata.node.impl

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
class ConnNode(
        @JsonIgnore
        override val def: NodeDef,
        val cp: ConnProfile
) : AbstractNode(def) {
    override val name = cp.form.connName
}