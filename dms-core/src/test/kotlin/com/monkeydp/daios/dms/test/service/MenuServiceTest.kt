package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.service.contract.MenuService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/28
 */
class MenuServiceTest : AbstractServiceTest() {
    
    @Autowired
    private lateinit var service: MenuService
    
    @Test
    fun loadConnMenuTest() {
        // TODO
    }
}