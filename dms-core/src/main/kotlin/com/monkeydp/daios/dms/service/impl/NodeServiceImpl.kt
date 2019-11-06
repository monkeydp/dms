package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadCtx
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.NodeService
import com.monkeydp.daios.dms.session.UserSession
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
            val bundle = registry.getBundle(cp)
            val connNode = bundle.apis.nodeApi.loadConnNode(cp)
            connNodes.add(connNode)
        }
        return connNodes.toList()
    }
    
    override fun loadSubNodes(ctx: NodeLoadCtx): List<Node> {
        val cpId = ctx.cpId
        val cp = connService.findCp(cpId)
        val conn = connService.findConn(cpId)
        ctx.conn = conn
        val bundle = registry.getBundle(cp)
        return bundle.apis.nodeApi.loadSubNodes(ctx)
    }
}