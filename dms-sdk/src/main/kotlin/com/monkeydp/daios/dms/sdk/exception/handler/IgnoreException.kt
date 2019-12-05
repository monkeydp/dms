package com.monkeydp.daios.dms.sdk.exception.handler

import com.monkeydp.tools.ext.DevMode
import com.monkeydp.tools.ext.DevMode.DEBUG
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/5
 */
annotation class IgnoreException(
        val exKClass: KClass<out Throwable>,
        val inDevMode: DevMode = DEBUG
)