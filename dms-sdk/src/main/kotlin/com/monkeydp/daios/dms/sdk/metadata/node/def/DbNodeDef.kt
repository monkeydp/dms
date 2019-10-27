package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.DB_ICON
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget.DB
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/27
 */
class DbNodeDef : AbstractNodeDef() {
    override val info = NodeInfo(DB, "", DB_ICON)
}