package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.RegisterItem.SET
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.PROPERTY

/**
 * @author iPotato
 * @date 2019/12/14
 */
@KodeinComponent<Any>(registerItems = [SET])
@Target(FIELD)
annotation class SdkDsDef