package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.node.impl.ConnNode

/**
 * @author iPotato
 * @date 2019/10/28
 */
interface NodeService {
    fun loadConnNodes(): List<ConnNode>
}