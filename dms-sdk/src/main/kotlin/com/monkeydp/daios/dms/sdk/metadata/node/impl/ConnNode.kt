package com.monkeydp.daios.dms.sdk.metadata.node.impl

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
class ConnNode(
        def: NodeDef,
        val cp: ConnProfile
) : AbstractNode(def, cp.form.connName)