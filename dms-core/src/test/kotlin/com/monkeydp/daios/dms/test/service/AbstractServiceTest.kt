package com.monkeydp.daios.dms.test.service

import com.monkeydp.daios.dms.config.kodein
import com.monkeydp.daios.dms.module.ModuleTestdata
import com.monkeydp.daios.dms.sdk.share.request.RequestContext
import com.monkeydp.daios.dms.test.AbstractTest
import org.junit.Before
import org.kodein.di.generic.instance

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractServiceTest : AbstractTest() {
    
    val ctx: RequestContext by kodein.instance()
    
    @Before
    fun before() {
        ctx.setRequestAttributes {
            cp = ModuleTestdata.cp
        }
    }
}