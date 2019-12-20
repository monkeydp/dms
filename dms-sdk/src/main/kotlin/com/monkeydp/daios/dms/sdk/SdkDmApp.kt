package com.monkeydp.daios.dms.sdk

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author iPotato
 * @date 2019/12/15
 */
@Target(CLASS)
annotation class SdkDmApp(val datasource: Datasource)