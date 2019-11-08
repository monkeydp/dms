package com.monkeydp.daios.dms.sdk.enumx

import com.monkeydp.tools.useful.Null
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@Target(CLASS)
annotation class SdkEnum(val parent: KClass<*> = Null::class)