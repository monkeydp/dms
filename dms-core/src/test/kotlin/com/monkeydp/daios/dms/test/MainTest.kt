package com.monkeydp.daios.dms.test

import org.junit.Test
import org.springframework.web.context.request.RequestContextHolder

/**
 * @author iPotato
 * @date 2019/10/6
 */
class MainTest {
    @Test
    fun test() {
        RequestContextHolder.getRequestAttributes()
    }
}