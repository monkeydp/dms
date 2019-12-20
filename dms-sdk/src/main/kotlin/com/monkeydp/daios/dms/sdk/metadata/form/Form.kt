package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.metadata.form.item.FormItem
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface Form {
    val items: List<FormItem>
}

fun form(items: List<FormItem>): Form = StdFrom(items)