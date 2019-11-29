package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/11/3
 */
class StdNode(
        defId: Int,
        target: Target<*>,
        name: String,
        icon: Icon<*>,
        childTargets: List<Target<*>>
) : AbstractNode(defId, target, name, icon, childTargets)