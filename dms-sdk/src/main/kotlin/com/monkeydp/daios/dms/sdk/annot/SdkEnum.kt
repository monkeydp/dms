package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.enumx.NullEnumx
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@KodeinComponent<KClass<*>>(Type.K_CLASS)
@Target(CLASS)
annotation class SdkEnum(val parent: KClass<out Enumx<*>> = NullEnumx::class)