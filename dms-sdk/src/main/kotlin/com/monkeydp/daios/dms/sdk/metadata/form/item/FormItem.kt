package com.monkeydp.daios.dms.sdk.metadata.form.item

import com.monkeydp.daios.dms.sdk.metadata.JsType
import com.monkeydp.daios.dms.sdk.metadata.UiComponent

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
    
    val component: UiComponent
    
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
}