package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class ConnNode(
        override val def: NodeDef,
        val cp: ConnectionProfile
) : AbstractNode(def) {
    override val name = CpForm(cp.userInput).connName
}