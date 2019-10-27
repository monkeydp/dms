package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.TABLE_ICON
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget.TABLE
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/27
 */
class TableNodeDef : AbstractNodeDef() {
    override val info = NodeInfo(TABLE, "", TABLE_ICON)
}