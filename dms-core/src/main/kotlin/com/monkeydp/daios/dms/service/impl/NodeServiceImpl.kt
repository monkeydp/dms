package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.ui.node.ConnNode
import com.monkeydp.daios.dms.sdk.ui.node.Node
import com.monkeydp.daios.dms.sdk.ui.node.NodePath
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.share.kodein.findImpl
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
    
    private val api: NodeApi get() = dmKodeinRepo.findImpl()
    
    override fun loadConnNodes(): List<ConnNode> =
            cpService.findAllByUserId(session.userId).run {
                groupBy { it.datasource }.map {
                    val api: NodeApi = dmKodeinRepo.findImpl(it.key)
                    api.loadConnNodes(it.value)
                }.flatten()
            }
    
    override fun loadSubNodes(path: NodePath): List<Node> = api.loadSubNodes(path)
}