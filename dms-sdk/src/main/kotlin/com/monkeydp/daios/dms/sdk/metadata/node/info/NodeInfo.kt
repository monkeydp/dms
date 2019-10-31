package com.monkeydp.daios.dms.sdk.metadata.node.info

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeInfo(
        val target: Target<*>,
        val name: String = "",
        val icon: Icon<*> = EMPTY_ICON
)