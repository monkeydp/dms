package com.monkeydp.daios.dms.sdk.mock

import com.monkeydp.daios.dms.sdk.entity.Example
import com.monkeydp.daios.dms.sdk.faker

/**
 * @author iPotato
 * @date 2019/10/23
 */
object ExampleMocker {

    val examples = listOf<Example>(mock(), mock())

    fun mock() = Example(
            name = faker.name().fullName(),
            status = Example.Status.random()
    )
}