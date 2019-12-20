package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/27
 */
interface ConnNode : Node {
    val cp: ConnProfile
}

fun connNode(
        cp: ConnProfile,
        defId: Int,
        target: Target<*>,
        icon: Icon<*>
): ConnNode = StdConnNode(cp, defId, target, icon)