package com.monkeydp.daios.dms.sdk.exception.handler

import com.monkeydp.tools.config.DevMode
import com.monkeydp.tools.config.DevMode.DEBUG
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.reflect.KClass

/**
 * Ignore exception in specific dev mode
 *
 * If throw exception, the fun would return null (May cause NullPointerException)
 *
 * If you don't want to see NullPointerException, please only use this annotation on function that returns Unit
 *
 * @author iPotato
 * @date 2019/12/5
 */
@Target(FUNCTION)
annotation class IgnoreException(
        val exKClass: KClass<out Throwable>,
        val inDevMode: DevMode = DEBUG
)