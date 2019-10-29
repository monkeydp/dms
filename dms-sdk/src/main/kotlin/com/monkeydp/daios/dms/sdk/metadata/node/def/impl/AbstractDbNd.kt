package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.def.DbNd

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDbNd : DbNd, AbstractNd() {
    override val info = NodeInfo(GlobalTarget.DB, "", GlobalIcon.DB_ICON)
}