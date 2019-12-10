package com.monkeydp.daios.dms.sdk.main

import com.monkeydp.daios.dms.sdk.main.SdkImpl.Type.SINGLETON
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS

/**
 * @author iPotato
 * @date 2019/12/10
 */
@Target(ANNOTATION_CLASS)
annotation class SdkImpl(val type: Type = SINGLETON) {
    enum class Type {
        SINGLETON,
        KClass
    }
}