package com.monkeydp.daios.dms.example

import com.monkeydp.daios.dms.curd.repository.CurdRepo
import com.monkeydp.daios.dms.sdk.example.Example
import org.springframework.stereotype.Repository

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Repository
interface ExampleRepo : CurdRepo<Example>