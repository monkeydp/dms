package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.InstrApi
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.daios.dms.service.contract.InstrService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class InstrServiceImpl : InstrService {
    @Lazy
    @Autowired
    lateinit var apiMap: Map<Datasource, InstrApi>
    
    override fun parse(ctx: InstrParsingCtx) {
        val ds = RequestContext.datasource
        val api = apiMap.getValue(ds)
        api.parse(ctx)
    }
}