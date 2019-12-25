package com.monkeydp.daios.dms.test.curd.service

import com.monkeydp.daios.dms.example.ExampleService
import com.monkeydp.daios.dms.sdk.example.ExampleMocker
import com.monkeydp.daios.dms.test.AbstractTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/22
 */
class CurdServiceTest : AbstractTest() {
    
    @Autowired
    private lateinit var service: ExampleService
    
    @Test
    fun saveTest() {
        val example = service.save(ExampleMocker.mock())
        assertTrue(example.id > ExampleMocker.examples.size)
    }
    
    @Test
    fun findByIdTest() {
        val example = ExampleMocker.examples.get(0)
        val found = service.findById(example.id)
        assertEquals(example, found)
    }
    
    @Test
    fun findAllTest() {
        val examples = service.findAll()
        ExampleMocker.examples.forEach { example ->
            assertTrue(examples.contains(example))
        }
    }
    
    @Test
    fun deleteTest() {
        assertThrows<NoSuchElementException> {
            val example = service.save(ExampleMocker.mock())
            val id = example.id
            service.delete(example)
            service.findById(id)
        }
    }
    
    @Test
    fun deleteByIdTest() {
        assertThrows<NoSuchElementException> {
            val example = service.save(ExampleMocker.mock())
            val id = example.id
            service.deleteById(id)
            service.findById(id)
        }
    }
}