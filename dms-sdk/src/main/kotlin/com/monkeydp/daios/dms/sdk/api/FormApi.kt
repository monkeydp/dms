package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.main.SdkApiContract
import com.monkeydp.daios.dms.sdk.metadata.form.Form
import com.monkeydp.daios.dms.sdk.metadata.form.FormLoadingCtx

/**
 * @author iPotato
 * @date 2019/10/25
 */
@SdkApiContract
interface FormApi {
    fun loadFrom(ctx: FormLoadingCtx): Form
}