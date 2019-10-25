package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.MetadataLoader

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeLoader : MetadataLoader {
    fun loadNodeInfos(ctx: NodeLoadContext): List<NodeInfo>
}