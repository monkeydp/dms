package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.module.ModuleTestdata
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.share.request.RequestContextHolder
import com.monkeydp.daios.dms.test.AbstractTest
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

/**
 * @author iPotato
 * @date 2019/10/28
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractServiceTest : AbstractTest() {
    
    @BeforeAll
    fun beforeAll() {
        RequestContextHolder.setRequestAttributes(
                ConnContext(ModuleTestdata.cp)
        )
    }
}