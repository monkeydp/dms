package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.service.contract.NodeService
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/28
 */
class NodeServiceTest : AbstractServiceTest() {
    
    @Autowired
    private lateinit var service: NodeService
    
    @Test
    fun loadConnNodesTest() {
        val connNodes = service.getConnNodes()
        Assert.assertTrue(connNodes.isNotEmpty())
    }
}