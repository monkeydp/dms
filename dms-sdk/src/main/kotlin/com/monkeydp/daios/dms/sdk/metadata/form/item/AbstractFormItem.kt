package com.monkeydp.daios.dms.sdk.metadata.form.item

import com.monkeydp.daios.dms.sdk.metadata.JsType
import com.monkeydp.daios.dms.sdk.metadata.UiComponent

/**
 * @author iPotato
 * @date 2019/11/8
 */
abstract class AbstractFormItem(
        override val label: String,
        override val name: String,
        
        override val desc: String,
        override val jsType: JsType,
        override val component: UiComponent,
        
        override val required: Boolean,
        override val hidden: Boolean,
        override val disabled: Boolean
) : FormItem