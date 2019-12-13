package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author iPotato
 * @date 2019/11/8
 */
@KodeinComponent<Any>
@Target(CLASS)
annotation class SdkApi {
}