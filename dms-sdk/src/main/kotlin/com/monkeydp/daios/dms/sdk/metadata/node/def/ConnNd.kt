package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.main.ConnNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface ConnNd : NodeDef {
    fun create(cp: ConnProfile): ConnNode
}