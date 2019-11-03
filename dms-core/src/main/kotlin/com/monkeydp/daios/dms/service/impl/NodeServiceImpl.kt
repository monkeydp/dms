package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.component.UserSession
import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.node.main.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.main.Node
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtxForm
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Service
internal class NodeServiceImpl : NodeService {
    
    @Autowired
    private lateinit var session: UserSession
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var cpService: ConnProfileService
    @Autowired
    private lateinit var connService: ConnService
    
    override fun loadConnNodes(): List<ConnNode> {
        val userId = session.userId
        val cps = cpService.findAllByUserId(userId)
        val connNodes = mutableListOf<ConnNode>()
        cps.forEach { cp ->
            val def = registry.getConnNodeDef(cp.datasource)
            connNodes.add(def.create(cp))
        }
        return connNodes.toList()
    }
    
    override fun loadSubNodes(ctx: NodeLoadCtxForm): List<Node> {
        val cpId = ctx.cpId
        val cp = connService.findCp(cpId)
        val conn = connService.findConn(cpId)
        val bundle = registry.getBundle(cp)
        return bundle.apis.nodeApi.loadSubNodes(ctx.toInner(conn))
    }
}