package com.monkeydp.daios.dms.sdk.metadata.node.main

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
class ConnNode(
        def: NodeDef,
        val cp: ConnProfile
) : AbstractNode(def, cp.form.connName)