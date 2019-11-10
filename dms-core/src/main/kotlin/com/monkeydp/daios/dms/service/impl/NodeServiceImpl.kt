package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadingCtx
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.NodeService
import com.monkeydp.daios.dms.session.UserSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Service
internal class NodeServiceImpl : NodeService {
    
    @Autowired
    private lateinit var session: UserSession
    @Lazy
    @Autowired
    lateinit var apiMap: Map<Datasource, NodeApi>
    @Autowired
    private lateinit var cpService: ConnProfileService
    @Autowired
    private lateinit var connService: ConnService
    
    override fun loadConnNodes(): List<ConnNode> {
        val userId = session.userId
        val cps = cpService.findAllByUserId(userId)
        val connNodes = mutableListOf<ConnNode>()
        cps.forEach { cp ->
            val api = apiMap.getValue(cp.datasource)
            val connNode = api.loadConnNode(cp)
            connNodes.add(connNode)
        }
        return connNodes.toList()
    }
    
    override fun loadSubNodes(ctx: NodeLoadingCtx): List<Node> {
        val cpId = ctx.cpId
    
        val conn = connService.findConn(cpId)
        ctx.conn = conn
    
        val ds = connService.findDatasource(cpId)
        val api = apiMap.getValue(ds)
        
        return api.loadSubNodes(ctx)
    }
}