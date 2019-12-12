package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.annot.SdkFormItem
import com.monkeydp.daios.dms.sdk.metadata.form.item.FormItem
import com.monkeydp.daios.dms.sdk.metadata.form.item.StdFormItem
import com.monkeydp.tools.ext.camelCaseSeparated
import com.monkeydp.tools.ext.getAnnotatedProps
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * @author iPotato
 * @date 2019/11/8
 */
object FormBuilder {
    
    fun buildForm(clazz: KClass<*>): Form {
        val props = clazz.getAnnotatedProps<SdkFormItem>()
        val items = mutableListOf<FormItem>()
        props.forEach {
            val annot = it.findAnnotation<SdkFormItem>()!!
            val propName = it.name
            val item = StdFormItem(
                    label = if (annot.label.isNotEmpty()) annot.label else propName.camelCaseSeparated(true),
                    name = if (annot.name.isNotEmpty()) annot.name else propName,
                    desc = annot.desc,
                    jsType = annot.jsType,
                    component = annot.component,
                    required = annot.required,
                    hidden = annot.hidden,
                    disabled = annot.disabled
            )
            items.add(item)
        }
        return StdFrom(items.toList())
    }
}