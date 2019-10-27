package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.GROUP_ICON
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget.GROUP
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractGroupNodeDef : AbstractNodeDef() {
    override val info = NodeInfo(GROUP, "Group", GROUP_ICON)
}