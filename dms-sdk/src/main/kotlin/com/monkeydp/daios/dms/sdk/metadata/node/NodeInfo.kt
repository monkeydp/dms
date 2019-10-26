package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import javax.swing.Icon

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeInfo(
        val target: Target,
        val name: String,
        val icon: Icon
)