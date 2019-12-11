package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.FormApi
import com.monkeydp.daios.dms.sdk.dm.DmHelper
import com.monkeydp.daios.dms.sdk.metadata.form.FormLoadingCtx
import com.monkeydp.daios.dms.service.contract.FormService
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class FormServiceImpl : FormService {
    
    private val api: FormApi get() = DmHelper.findImpl()
    
    override fun loadForm(ctx: FormLoadingCtx) = api.loadFrom(ctx)
}