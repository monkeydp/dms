package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.InstrApi
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findImpl
import com.monkeydp.daios.dms.sdk.instruction.InstrParsingParams
import com.monkeydp.daios.dms.service.contract.InstrService
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class InstrServiceImpl : InstrService {
    
    private val api: InstrApi get() = dmKodeinRepo.findImpl()
    
    override fun parse(params: InstrParsingParams) = api.parse(params)
}