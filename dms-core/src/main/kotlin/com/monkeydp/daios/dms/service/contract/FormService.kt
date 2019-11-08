package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.metadata.form.Form
import com.monkeydp.daios.dms.sdk.metadata.form.FormLoadingCtx

/**
 * @author iPotato
 * @date 2019/11/1
 */
interface FormService {
    fun loadForm(ctx: FormLoadingCtx): Form
}