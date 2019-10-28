package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.service.contract.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author iPotato
 * @date 2019/10/28
 */
@RestController
@RequestMapping("node")
class NodeController {
    
    @Autowired
    private lateinit var service: NodeService
    
    @GetMapping
    fun loadConnNodes() = service.loadConnNodes()
}