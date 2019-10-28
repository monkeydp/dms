package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractGnd : AbstractNd() {
    override val info = NodeInfo(GlobalTarget.GROUP, "Group", GlobalIcon.GROUP_ICON)
}