package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/11/3
 */
data class StdNode(
        override val target: Target<*>,
        override val name: String,
        override val icon: Icon<*>
) : Node