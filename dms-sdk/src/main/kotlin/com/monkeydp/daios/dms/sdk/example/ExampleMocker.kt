package com.monkeydp.daios.dms.sdk.example

import com.monkeydp.daios.dms.sdk.faker

/**
 * @author iPotato
 * @date 2019/10/23
 */
object ExampleMocker {

    val examples = mutableListOf<Example>(mock(), mock())

    fun mock() = Example(
            name = faker.name().fullName(),
            status = Example.Status.random()
    )
}