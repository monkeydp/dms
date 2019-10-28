package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.boot.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.node.impl.ConnNode
import com.monkeydp.daios.dms.service.contract.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Service
class NodeServiceImpl : NodeService {
    
    @Autowired
    private lateinit var registry: ModuleRegistry
    
    override fun loadConnNodes(): List<ConnNode> {
        // TODO
        return emptyList()
    }
}