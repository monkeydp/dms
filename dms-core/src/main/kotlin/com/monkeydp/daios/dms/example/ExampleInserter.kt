package com.monkeydp.daios.dms.example

import com.monkeydp.daios.dms.curd.service.contract.ExampleService
import com.monkeydp.daios.dms.sdk.mock.ExampleMocker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/23
 */
@Component
class ExampleInserter : CommandLineRunner {
    @Autowired
    private lateinit var service: ExampleService

    override fun run(vararg args: String?) {
        ExampleMocker.examples.forEach { example -> service.save(example) }
    }
}