package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.module.ModuleTestdata
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.share.request.RequestContextHolder
import com.monkeydp.daios.dms.test.AbstractTest
import org.junit.Before

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractServiceTest : AbstractTest() {
    
    @Before
    fun before() {
        RequestContextHolder.setRequestAttributes(
                ConnContext {
                    cp = ModuleTestdata.cp
                }
        )
    }
}