package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import java.util.*

/**
 * @author iPotato
 * @date 2019/11/29
 */
abstract class AbstractNode(
        override val defId: Int,
        override val target: Target<*>,
        override val name: String,
        override val icon: Icon<*>,
        override val childTargets: List<Target<*>>
) : Node