package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import java.util.*

/**
 * @author iPotato
 * @date 2019/11/3
 */
class StdNode(
        defUuid: UUID,
        target: Target<*>,
        name: String,
        icon: Icon<*>,
        childTargets: List<Target<*>>
) : AbstractNode(defUuid, target, name, icon, childTargets)