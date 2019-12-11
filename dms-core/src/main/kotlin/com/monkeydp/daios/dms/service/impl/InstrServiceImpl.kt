package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.InstrApi
import com.monkeydp.daios.dms.sdk.dm.DmHelper
import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx
import com.monkeydp.daios.dms.service.contract.InstrService
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class InstrServiceImpl : InstrService {
    
    private val api: InstrApi get() = DmHelper.findImpl()
    
    override fun parse(ctx: InstrParsingCtx) = api.parse(ctx)
}