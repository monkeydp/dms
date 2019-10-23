package com.monkeydp.daios.dms.test.curd.service

import com.monkeydp.daios.dms.curd.service.contract.ExampleService
import com.monkeydp.daios.dms.sdk.mock.ExampleMocker
import com.monkeydp.daios.dms.test.BaseTest
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/22
 */
class CurdServiceTest : BaseTest() {

    @Autowired
    private lateinit var service: ExampleService

    @Test
    fun saveTest() {
        val entity = service.save(ExampleMocker.mock())
        Assert.assertTrue(entity.id > 0)
    }

    @Test
    fun findAllTest() {
        val examples = service.findAll()
        Assert.assertEquals(examples, ExampleMocker.examples)
    }
}