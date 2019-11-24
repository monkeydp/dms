package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.FormApi
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.form.Form
import com.monkeydp.daios.dms.sdk.metadata.form.FormLoadingCtx
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.daios.dms.service.contract.FormService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class FormServiceImpl : FormService {
    
    @Lazy
    @Autowired
    lateinit var apiMap: Map<Datasource, FormApi>
    
    override fun loadForm(ctx: FormLoadingCtx): Form {
        val ds = RequestContext.datasource
        val api = apiMap.getValue(ds)
        return api.loadFrom(ctx)
    }
}