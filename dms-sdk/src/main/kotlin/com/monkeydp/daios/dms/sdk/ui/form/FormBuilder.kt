package com.monkeydp.daios.dms.sdk.ui.form

import com.monkeydp.daios.dms.sdk.received.form.ReceivedForm
import com.monkeydp.daios.dms.sdk.received.form.annot.ReceivedFormItem
import com.monkeydp.tools.ext.kotlin.camelCaseSeparated
import com.monkeydp.tools.ext.kotlin.findAnnot
import com.monkeydp.tools.ext.kotlin.getAnnotatedProps
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
object FormBuilder {
    
    fun buildForm(kClass: KClass<out ReceivedForm>): Form {
        val props = kClass.getAnnotatedProps<ReceivedFormItem>()
        val items = mutableListOf<FormItem>()
        props.forEach {
            val annot = it.findAnnot<ReceivedFormItem>()
            val propName = it.name
            val item = FormItem(
                    label = if (annot.label.isNotEmpty()) annot.label else propName.camelCaseSeparated(true),
                    name = if (annot.name.isNotEmpty()) annot.name else propName,
                    desc = annot.desc,
                    jsType = annot.jsType,
                    compType = annot.compType,
                    required = annot.required,
                    hidden = annot.hidden,
                    disabled = annot.disabled
            )
            items.add(item)
        }
        return Form(items.toList())
    }
}