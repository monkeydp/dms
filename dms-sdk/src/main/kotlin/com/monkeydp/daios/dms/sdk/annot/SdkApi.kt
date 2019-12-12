package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.tools.kodein.KodeinComponent
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author iPotato
 * @date 2019/11/8
 */
@KodeinComponent
@Target(CLASS)
annotation class SdkApi {
}