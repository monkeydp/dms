package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.RegisterItem.SET
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * @author iPotato
 * @date 2019/12/14
 */
@KodeinComponent<Any>(registerItems = [SET])
@Target(FIELD)
annotation class SdkTestCp