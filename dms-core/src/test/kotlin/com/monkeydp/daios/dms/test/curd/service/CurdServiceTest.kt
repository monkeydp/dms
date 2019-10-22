package com.monkeydp.daios.dms.test.curd.service

import com.monkeydp.daios.dms.curd.service.contract.ExampleService
import com.monkeydp.daios.dms.sdk.model.ExampleModel
import com.monkeydp.daios.dms.sdk.model.ExampleModel.ExampleModelStatus.ENABLED
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

    private val mockExampleModel = ExampleModel(
            name = "iPotato",
            status = ENABLED
    )

    @Test
    fun saveTest() {
        val savedModel = service.save(mockExampleModel)
        Assert.assertTrue(savedModel.id > 0)
    }
}