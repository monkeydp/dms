package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.daios.dms.sdk.metadata.JsType
import com.monkeydp.daios.dms.sdk.metadata.JsType.STRING
import com.monkeydp.daios.dms.sdk.metadata.UiComponent
import com.monkeydp.daios.dms.sdk.metadata.UiComponent.INPUT
import kotlin.annotation.AnnotationTarget.PROPERTY

/**
 * @author iPotato
 * @date 2019/11/8
 */
@Target(PROPERTY)
annotation class SdkFormItem(
        /**
         * If empty, the prop name is used
         * Camelcase prop name would be separated by space, capitalize every word
         */
        val label: String = "",
        /**
         * If empty, the prop name is used
         */
        val name: String = "",
        val desc: String = "",
        val jsType: JsType = STRING,
        val component: UiComponent = INPUT,
        val dataSpEl: String = "",
        val required: Boolean = false,
        val hidden: Boolean = false,
        val disabled: Boolean = false
)