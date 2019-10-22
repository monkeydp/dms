package com.monkeydp.daios.dms.curd.service.impl

import com.monkeydp.daios.dms.curd.repository.ExampleRepo
import com.monkeydp.daios.dms.curd.service.contract.ExampleService
import com.monkeydp.daios.dms.sdk.model.ExampleModel
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Service
internal class ExampleServiceImpl :
        ExampleService,
        CurdServiceImpl<ExampleModel, ExampleRepo>()