package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.CONN_ICON
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget.CONN
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/27
 */
class ConnNodeDef : AbstractNodeDef() {
    override val info = NodeInfo(CONN, "", CONN_ICON)
}