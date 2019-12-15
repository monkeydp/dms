package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.share.kodein.DmShareKodeinHelper
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeLoadingCtx
import com.monkeydp.daios.dms.service.contract.NodeService
import com.monkeydp.daios.dms.session.UserSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Service
internal class NodeServiceImpl @Autowired constructor(
        private val session: UserSession,
        private val cpService: ConnProfileService
) : NodeService {
    
    private val api: NodeApi get() = DmShareKodeinHelper.findImpl()
    
    override fun loadConnNodes(): List<ConnNode> {
        val userId = session.userId
        val cps = cpService.findAllByUserId(userId)
        val connNodes = mutableListOf<ConnNode>()
        cps.forEach { cp ->
            val api = DmShareKodeinHelper.findImpl<NodeApi>(cp.datasource)
            val connNode = api.loadConnNode(cp)
            connNodes.add(connNode)
        }
        return connNodes.toList()
    }
    
    override fun loadSubNodes(ctx: NodeLoadingCtx): List<Node> {
        val api = DmShareKodeinHelper.findImpl<NodeApi>()
        return api.loadSubNodes(ctx)
    }
}