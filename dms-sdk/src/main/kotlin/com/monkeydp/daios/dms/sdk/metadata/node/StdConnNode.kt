package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/25
 */
internal class StdConnNode(
        cp: ConnProfile,
        defId: Int,
        target: Target<*>,
        icon: Icon<*>
) : AbstractConnNode(cp, defId, target, icon)