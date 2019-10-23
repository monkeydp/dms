package com.monkeydp.daios.dms.test.curd.service

import com.monkeydp.daios.dms.example.ExampleService
import com.monkeydp.daios.dms.sdk.example.ExampleMocker
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
        Assert.assertTrue(entity.id > ExampleMocker.examples.size)
    }

    @Test
    fun findByIdTest() {
        val example = ExampleMocker.examples.get(0)
        val found = service.findById(example.id)
        Assert.assertEquals(example, found)
    }

    @Test
    fun findAllTest() {
        val examples = service.findAll()
        ExampleMocker.examples.forEach { example ->
            Assert.assertTrue(examples.contains(example))
        }
    }

    @Test
    fun deleteTest() {
        val example = service.save(ExampleMocker.mock())
        val id = example.id
        service.findById(id)
        service.delete(example)
        try {
            service.findById(id)
        } catch (e: NoSuchElementException) {
            return
        }
        Assert.assertTrue(false)
    }

    @Test
    fun deleteByIdTest() {
        val example = service.save(ExampleMocker.mock())
        val id = example.id
        service.findById(id)
        service.deleteById(id)
        try {
            service.findById(id)
        } catch (e: NoSuchElementException) {
            return
        }
        Assert.assertTrue(false)
    }
}