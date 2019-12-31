package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.module.ModuleTestdata.cp
import com.monkeydp.daios.dms.sdk.context.ConnContext
import com.monkeydp.daios.dms.sdk.context.ContextRepoHolder
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.test.AbstractTest
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/28
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractServiceTest : AbstractTest() {
    
    @Autowired
    private lateinit var connService: ConnService
    
    @BeforeAll
    fun beforeAll() {
        ContextRepoHolder.setContextRepo {
            connContext = ConnContext(cp)
        }
    }
}