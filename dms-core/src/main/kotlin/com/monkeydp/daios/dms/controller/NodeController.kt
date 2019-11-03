package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtxForm
import com.monkeydp.daios.dms.service.contract.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author iPotato
 * @date 2019/10/28
 */
@RestController
@RequestMapping("node")
class NodeController {
    
    @Autowired
    private lateinit var service: NodeService
    
    @GetMapping("load-conn-nodes")
    fun loadConnNodes() = service.loadConnNodes()
    
    @PostMapping("load-sub-nodes")
    fun loadSubNodes(@RequestBody @NeedDatasource ctx: NodeLoadCtxForm) = service.loadSubNodes(ctx)
}