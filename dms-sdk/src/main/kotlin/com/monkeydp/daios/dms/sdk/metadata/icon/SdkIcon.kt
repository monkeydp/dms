package com.monkeydp.daios.dms.sdk.metadata.icon

import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/18
 */
@Target(CLASS)
@KodeinComponent(SdkIcon.KodeinBuilderConfig::class)
annotation class SdkIcon {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<KClass<out Icon<*>>>() with singletonX { comp.annotatedKClass }
        }
    }
}