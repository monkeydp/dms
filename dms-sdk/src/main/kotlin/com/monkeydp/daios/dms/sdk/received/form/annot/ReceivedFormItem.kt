package com.monkeydp.daios.dms.sdk.received.form.annot

import com.monkeydp.daios.dms.sdk.ui.JsType
import com.monkeydp.daios.dms.sdk.ui.JsType.STRING
import com.monkeydp.daios.dms.sdk.ui.UiCompType
import com.monkeydp.daios.dms.sdk.ui.UiCompType.INPUT
import kotlin.annotation.AnnotationTarget.PROPERTY

/**
 * @author iPotato
 * @date 2019/11/8
 */
@Target(PROPERTY)
annotation class ReceivedFormItem(
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
        val compType: UiCompType = INPUT,
        val dataSpEl: String = "",
        val required: Boolean = false,
        val hidden: Boolean = false,
        val disabled: Boolean = false
)