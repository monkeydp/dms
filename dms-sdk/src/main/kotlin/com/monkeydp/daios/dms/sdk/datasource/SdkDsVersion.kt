package com.monkeydp.daios.dms.sdk.datasource

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
 * @date 2019/12/20
 */
@Target(CLASS)
@KodeinComponent(SdkDsVersion.KodeinBuilderConfig::class)
annotation class SdkDsVersion {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<KClass<out DsVersion<*>>>() with singletonX { comp.annotatedKClass }
        }
    }
}