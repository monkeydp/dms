package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.def.ConnNd

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractConnNd : ConnNd, AbstractNd() {
    override val info = NodeInfo(GlobalTarget.CONN, "", GlobalIcon.CONN_ICON)
}