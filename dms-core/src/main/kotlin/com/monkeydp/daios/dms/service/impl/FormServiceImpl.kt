package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.form.Form
import com.monkeydp.daios.dms.sdk.metadata.form.FormLoadingCtx
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.FormService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class FormServiceImpl : FormService {
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var connService: ConnService
    
    override fun loadForm(ctx: FormLoadingCtx): Form {
        val cp = connService.findCp(ctx.cpId)
        val bundle = registry.getBundle(cp)
        return bundle.apis.formApi.loadFrom(ctx)
    }
}