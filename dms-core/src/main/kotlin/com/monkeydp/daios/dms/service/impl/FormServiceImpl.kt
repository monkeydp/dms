package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.FormApi
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.share.kodein.findImpl
import com.monkeydp.daios.dms.service.contract.FormService
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class FormServiceImpl : FormService {
    
    private val api: FormApi get() = dmKodeinRepo.findImpl()
    
    override fun loadForm(instruction: Instruction) = api.loadFrom(instruction)
}