package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.metadata.JsType
import com.monkeydp.daios.dms.sdk.metadata.UiCompType

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface FormItem {
    /**
     * The item name displayed in ui
     */
    val label: String
    /**
     * The name sent to backend
     */
    val name: String
    
    /**
     * Description
     */
    val desc: String
    
    val jsType: JsType
    
    val compType: UiCompType
    
    /**
     * If true, model is required
     */
    val required: Boolean
    
    /**
     * If true, form item is hidden
     */
    val hidden: Boolean
    
    /**
     * If true, ui component is not editable
     */
    val disabled: Boolean
    
    companion object {
        operator fun invoke(
                label: String,
                name: String,
                desc: String,
                jsType: JsType,
                compType: UiCompType,
                required: Boolean,
                hidden: Boolean,
                disabled: Boolean
        ): FormItem = StdFormItem(label, name, desc, jsType, compType, required, hidden, disabled)
    }
}

abstract class AbstractFormItem(
        override val label: String,
        override val name: String,
        
        override val desc: String,
        override val jsType: JsType,
        override val compType: UiCompType,
        
        override val required: Boolean,
        override val hidden: Boolean,
        override val disabled: Boolean
) : FormItem

private class StdFormItem(
        label: String,
        name: String,
        desc: String,
        jsType: JsType,
        compType: UiCompType,
        required: Boolean,
        hidden: Boolean,
        disabled: Boolean
) : AbstractFormItem(label, name, desc, jsType, compType, required, hidden, disabled)