package com.monkeydp.daios.dms.sdk.ui.node

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.ui.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/27
 */
interface ConnNode : Node {
    val cp: ConnProfile
    
    companion object {
        operator fun invoke(
                cp: ConnProfile,
                defId: Int,
                target: Target<*>,
                icon: Icon<*>,
                menuDefId: Int?
        ): ConnNode = StdConnNode(cp, defId, target, icon, menuDefId)
    }
}

abstract class AbstractConnNode(
        override val cp: ConnProfile,
        defId: Int,
        target: Target<*>,
        icon: Icon<*>,
        menuDefId: Int?
) : ConnNode, AbstractNode(defId, target, cp.form.connName, icon, menuDefId)

private class StdConnNode(
        cp: ConnProfile,
        defId: Int,
        target: Target<*>,
        icon: Icon<*>,
        menuDefId: Int?
) : AbstractConnNode(cp, defId, target, icon, menuDefId)