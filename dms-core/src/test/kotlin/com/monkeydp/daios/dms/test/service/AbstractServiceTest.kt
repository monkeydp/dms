package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.module.ModuleTestdata
import com.monkeydp.daios.dms.sdk.share.request.RequestContext
import com.monkeydp.daios.dms.test.AbstractTest
import org.junit.Before

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractServiceTest : AbstractTest() {
    @Before
    fun before() {
        RequestContext.setCp(ModuleTestdata.cp)
    }
}