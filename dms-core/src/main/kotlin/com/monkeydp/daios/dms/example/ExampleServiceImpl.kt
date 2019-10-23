package com.monkeydp.daios.dms.example

import com.monkeydp.daios.dms.curd.service.impl.CurdServiceImpl
import com.monkeydp.daios.dms.sdk.example.Example
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Service
internal class ExampleServiceImpl :
        ExampleService,
        CurdServiceImpl<Example, ExampleRepo>()