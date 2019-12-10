package com.monkeydp.daios.dms.sdk.main

import com.monkeydp.daios.dms.sdk.main.SdkImpl.Type
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.enumx.NullEnumx
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@SdkImpl(Type.KClass)
@Target(CLASS)
annotation class SdkEnum(val parent: KClass<out Enumx<*>> = NullEnumx::class)