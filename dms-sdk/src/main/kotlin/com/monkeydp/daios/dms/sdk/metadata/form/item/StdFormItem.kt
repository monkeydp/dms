package com.monkeydp.daios.dms.sdk.metadata.form.item

import com.monkeydp.daios.dms.sdk.metadata.JsType
import com.monkeydp.daios.dms.sdk.metadata.UiComponent

/**
 * @author iPotato
 * @date 2019/11/8
 */
class StdFormItem(label: String,
                  name: String,

                  desc: String,
                  jsType: JsType,
                  component: UiComponent,

                  required: Boolean,
                  hidden: Boolean,
                  disabled: Boolean
) : AbstractFormItem(label, name, desc, jsType, component, required, hidden, disabled)