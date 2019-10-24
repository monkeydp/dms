package com.monkeydp.daios.dms.example

import com.monkeydp.daios.dms.sdk.example.Example
import com.monkeydp.daios.dms.sdk.example.ExampleMocker.examples
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
        val saveds = mutableListOf<Example>()
        examples.forEach { example ->
            val saved = service.save(example)
            saveds.add(saved)
        }
        examples.clear()
        examples.addAll(saveds)
    }
}