package com.monkeydp.daios.dms.controller

import com.monkeydp.daios.dms.service.contract.NodeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Api(tags = ["Node"])
@RestController
@RequestMapping("node")
class NodeController @Autowired constructor(
        private val service: NodeService
) {
    @ApiOperation("Load connection nodes")
    @GetMapping("load-conn-nodes")
    fun loadConnNodes() = service.loadConnNodes()
    
    @ApiOperation("Load sub nodes")
    @PostMapping("load-sub-nodes")
    fun loadSubNodes() = service.loadSubNodes()
}