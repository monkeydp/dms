package com.monkeydp.daios.dms.test

import com.monkeydp.daios.dms.DmsApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DmsApplication::class])
abstract class AbstractTest {
}
