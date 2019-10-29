package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.def.TableNd

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractTableNd(name: String = "") : TableNd, AbstractNd() {
    override val info = NodeInfo(GlobalTarget.TABLE, name, GlobalIcon.TABLE_ICON)
}